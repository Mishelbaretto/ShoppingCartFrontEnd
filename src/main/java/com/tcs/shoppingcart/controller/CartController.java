package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.domain.Cart;

@Controller
public class CartController {
	
	
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private Cart cart;
	@Autowired
	private HttpSession httpSession;
	@PostMapping("/product/cart/add")
	public ModelAndView addToCart(@RequestParam String productName,@RequestParam int price, @RequestParam String quantity) {
		
		ModelAndView mv=new ModelAndView("home");
		String loggedInUserID=(String) httpSession.getAttribute("loggedInUserID");
		
		if(loggedInUserID==null) {
			mv.addObject("errorMessage", "please login to add any product to cart");
			return mv;
		}
		cart.setEmailID(loggedInUserID);
		cart.setPrice(price);
		cart.setQuantity(Integer.parseInt(quantity));
		if(cartDAO.save(cart)) {
			mv.addObject("errorMessage", "The product added to cart  successfully");
		}
		else {
			mv.addObject("errorMessage", "Could not add the product to cart");
		}
		return mv;
	}
	//get my cart details
	@GetMapping("/mycart/")
	public ModelAndView getMyCartDetails() {
		//it will return all the products which are added to cart
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		String loggedInUserID=(String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null) {
			mv.addObject("errorMessage", "Please login to see your cart details");
			return mv;
		}
		List<Cart> cartList=cartDAO.list(loggedInUserID);
		mv.addObject("cartList", cartList);
		return mv;
		
	}
	/*@GetMapping("/mycart")
	public ModelAndView myCart() {
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		return mv;
	}*/
	

}
