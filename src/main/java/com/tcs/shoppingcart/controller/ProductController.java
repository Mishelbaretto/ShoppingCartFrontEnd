package com.tcs.shoppingcart.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Product;
import com.tcs.util.FileUtil;

@Controller
public class ProductController {

	Logger log = LoggerFactory.getLogger(ProductController.class);
	// we need to call productdao methods
	// get, save, update, delete, list

	// 1.inject the productdao and productdomain objects
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Product product;

	@Autowired
	HttpSession httpSession;

	@Autowired
	FileUtil fileUtil;

	 private static final String imageDirectory="ShoppingCartImages";
	 private static String rootPath=System.getProperty("catalina.home");

	/*
	 * @GetMapping("/product/get") public ModelAndView getProduct(@RequestParam
	 * String id) { productDAO.get(id); ModelAndView mv=new
	 * ModelAndView("redirect:/home"); mv.addObject("selectedproduct", product);
	 * mv.addObject("isUserSelectedProduct", true);
	 * mv.addObject("selectedProductImage",
	 * rootPath+File.separator+imageDirectory+File.separator+id+".PNG"); return mv;
	 * }
	 */

	@GetMapping("/product/get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		log.debug("Start of the getSelectedProduct  method");
		ModelAndView mv = new ModelAndView("redirect:/");
		redirectAttributes.addFlashAttribute("selectedProduct", productDAO.get(id));
		redirectAttributes.addFlashAttribute("isUserSelectedProduct", true);
		redirectAttributes.addFlashAttribute("selectedProductImage", rootPath+File.separator+imageDirectory+File.separator+id+".PNG");
		redirectAttributes.addFlashAttribute("productID", id);

		// redirectAttributes.addFlashAttribute("productID", rootPath +File.separator
		// +imageDirectory +File.separator +id + ".PNG");
		log.debug("End of the getSelectedProduct  method");
		return mv;

	}

	@PostMapping("/product/save/")

	public ModelAndView saveProduct(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID, @RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file) {
		
		log.debug("Start of the saveProduct  method");
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");

		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		price = price.replace(",", "");// replaces 30,00 to 3000
		product.setPrice(Integer.parseInt(price));
		// Category(categoryDAO.get(categoryID));
		product.setCategoryId(categoryID);
		// Supplier(supplierDAO.get(supplierID));
		product.setSupplierId(supplierID);

		if (productDAO.save(product)) {
			mv.addObject("productSuccessMessage", "The Product created successfully");
			// calls upload image method if true
			if (fileUtil.fileCopyNIO(file, id + ".PNG")) {
				mv.addObject("uploadMessage", "product image sucessfully updated");
			} else {
				mv.addObject("uploadMessage", "could not upload image");
			}
			// fetch all the categries again
			// List<Product> products= productDAO.list();
			// and set to http session
			// httpSession.setAttribute("products", products);
		} else {
			mv.addObject("productErrorMessage", "could not create please contact admin ");
		}
		log.debug("End of the saveProduct  method");
		return mv;

	}

	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product) {

		log.debug("Start of the updateProduct  method");
		// navigate to homepage
		ModelAndView mv = new ModelAndView("home");
		// call save method of product
		if (productDAO.update(product) == true) {
			// add success
			mv.addObject("productSuccessMessage", "The product updated sucessfully");
		} else {
			// add failure message
			mv.addObject("productErrorMessage", "could not updated the product");
		}
		log.debug("End of the updateProduct  method");
		return mv;
	}

	@GetMapping("/product/delete/")
	// @RequestMapping(name="/product/get/{id}" , method=RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam String id) {
		
		log.debug("Start of the deleteProduct  method");

		// navigate to homepage
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");

		// mv.addObject("product", product);
		// based on id,fetch the details from productdao
		if (productDAO.delete(id) == true) {
			mv.addObject("successMessage", "The product deleted sucessfully");
		} else {
			mv.addObject("errorMessage", "could not delete the product");
		}
		log.debug("End of the deleteProduct  method");
		return mv;

	}

	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam String id) {
		
		log.debug("Start of the editProduct  method");
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// based on product id and fetch details
		product = productDAO.get(id);
		// mv.addObject("selectedProduct",product);
		httpSession.setAttribute("selectedProduct", product);
		System.out.println(product.getName());
		log.debug("End of the editProduct  method");
		return mv;
	}

	@GetMapping("/products")
	public ModelAndView getAllCategories() {
		
		log.debug("Start of the getAllCategories method");
		ModelAndView mv = new ModelAndView("home");
		List<Product> categories = productDAO.list();
		mv.addObject("products", categories);
		log.debug("End of the getAllCategories method");
		return mv;

	}

	@GetMapping("search")
	public ModelAndView searchProduct(@RequestParam("searchString") String searchString) {
		log.debug("Start of the searchProduct method");
		ModelAndView mv = new ModelAndView("home");
		List<Product> products = productDAO.search(searchString);
		mv.addObject("products", products);
		mv.addObject("isUserSearchedProduct", true);
		System.out.println("entering search" + searchString + " is/are : " + products.size());
		log.info("Number of products with search string " + searchString + " is/are : " + products.size());
		log.debug("Start of the searchProduct method");
		return mv;
	}

}
