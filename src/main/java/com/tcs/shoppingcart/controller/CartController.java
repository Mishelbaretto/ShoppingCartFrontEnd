package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Product;

@Controller
public class CartController {

	Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private Product product;
	@Autowired
	private Cart cart;
	@Autowired
	private HttpSession httpSession;

	/*@GetMapping("/buy")
	public ModelAndView order() {

		log.debug("Starting of the order method");
		ModelAndView mv = new ModelAndView("home");
		// there shud be one update method which take emailid as parameter
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if (cartDAO.update(loggedInUserID)) {
			// mv.addObject("isUserClickedBuy", true);
			mv.addObject("successMessage", "your oredr placed sucessfully");
		} else {
			mv.addObject("errorMessage", "your oredrcould not placed sucessfully");
		}
		log.debug("Ending of the order method");
		return mv;
	}*/

	/*
	 * @PostMapping("/product/cart/add") public ModelAndView addToCart(@RequestParam
	 * String productName,@RequestParam int price, @RequestParam String quantity) {
	 * 
	 * ModelAndView mv=new ModelAndView("home"); String loggedInUserID=(String)
	 * httpSession.getAttribute("loggedInUserID");
	 * 
	 * if(loggedInUserID==null) { mv.addObject("errorMessage",
	 * "please login to add any product to cart"); return mv; }
	 * cart.setEmailID(loggedInUserID); cart.setPrice(price);
	 * cart.setQuantity(Integer.parseInt(quantity)); if(cartDAO.save(cart)) {
	 * mv.addObject("errorMessage", "The product added to cart  successfully"); }
	 * else { mv.addObject("errorMessage", "Could not add the product to cart"); }
	 * return mv; }
	 */

	@GetMapping("/product/cart/add/{productID}")
	public ModelAndView addToCartUsingGetMapping(@PathVariable String productID) {

		log.debug("Starting of the addToCartUsingGetMapping method");

		// ModelAndView mv=new ModelAndView("home");
		ModelAndView mv = new ModelAndView("redirect:/");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null) {
			mv.addObject("cartErrorMessage", "please login to add any product to cart");
			return mv;
		}
		// get al the details from productDAO.get()
		product = productDAO.get(productID);
		cart.setEmailID(loggedInUserID);
		cart.setPrice(product.getPrice());
		cart.setProductID(productID);
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		cart.setId();// to generate random id
		if (cartDAO.save(cart)) {
			mv.addObject("sucessMessage", "The product added to cart  successfully");
		} else {
			mv.addObject("errorMessage", "Could not add the product to cart");
		}
		log.debug("End of the addToCartUsingGetMapping method");
		return mv;
	}

	
	
	@GetMapping("/remove/{id}")
	public ModelAndView removeProductFromCart(@PathVariable("id") int id)
	{
		log.debug("Starting of the method removeProductFromCart");
		ModelAndView mv= new ModelAndView("redirect:/mycart");		
		if (cartDAO.delete(id)==true){
			mv.addObject("cartProductSucccess", "Product deleted from cart");
			String loggedInUserID= (String) httpSession.getAttribute("loggedInUserId");
			List<Cart> usercart= cartDAO.list(loggedInUserID);
		}
		else{
			mv.addObject("cartProductFail", "Product could not be deleted from cart");
		}
		log.debug("Ending of the method removeProductFromCart");
		return mv;
	}
	
	
	// get my cart details
	@GetMapping("/mycart")
	public ModelAndView getMyCartDetails() {

		log.debug("Starting of the method getMyCartDetails");
		// it will return all the products which are added to cart
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login to see your cart details");
			return mv;
		}
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		if (cartList.isEmpty()) {
			mv.addObject("noItems", "Your cart is empty");
		}
		else 
		{
			mv.addObject("cartDetails", true);
			mv.addObject("cartList", cartList);
			mv.addObject("size", cartList.size());
			int cartsum = 0;
			for (Cart a:cartList) 
			{
				cartsum = cartsum + a.getPrice();
			}
			httpSession.setAttribute("cartsum", cartsum);
		}

		log.debug("not of products in cart " + cartList.size());
		log.debug("Ending of the method getMyCartDetails");
		return mv;

	}
	/*
	 * @GetMapping("/mycart") public ModelAndView myCart() { ModelAndView mv=new
	 * ModelAndView("home"); mv.addObject("isUserClickedMyCart", true); return mv; }
	 */

	
	@PostMapping("/checkout")
	public ModelAndView checkout() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedCheckout", true);
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		httpSession.setAttribute("cartList", cartList);
		int cartsum = 0;
		for (Cart a:cartList) {
			cartsum = cartsum + a.getPrice();
		}
		httpSession.setAttribute("cartsum", cartsum);
		int total = cartsum+100;
		httpSession.setAttribute("total", total);
		return mv;
	}
	
	@PostMapping("/placeOrder")
	public ModelAndView placeOrder(@RequestParam("emailID") String emailID){
	ModelAndView mv = new ModelAndView("home");
	String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
	if(cartDAO.deleteCart(loggedInUserID)==true)
	{
		List<Cart> cartList = cartDAO.list(loggedInUserID);
		httpSession.setAttribute("cartList", cartList);
	for (Cart a:cartList) {
			cartDAO.delete(a.getId());
	}
		mv.addObject("orderPlacedMessage", "Your Order Placed Successfully...Continue Shopping");
		mv.addObject("clickedPlaceOrder",true);
	}
	else
	{
		mv.addObject("orderPlacedMessage", "Sorry, Your order is not Successfully Placed.. Try again after some time !!!");
	}
	return mv;
}


}
