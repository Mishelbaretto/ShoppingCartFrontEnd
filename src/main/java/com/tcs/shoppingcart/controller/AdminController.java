package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class AdminController {
	
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;
	
	@Autowired
	private Supplier supplier;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private Product product;

	@Autowired
	private HttpSession httpSession;
	@GetMapping("/managecategories")
	public ModelAndView adminClickedCategories() {
		ModelAndView mv=new ModelAndView("home");
		
		
		mv.addObject("isAdminClickedManageCategories",true);
		List<Category> categories = categoryDAO.list();
		//and set to http session.
		httpSession.setAttribute("categories", categories);
		
		
		return mv;
	}
	
	@GetMapping("/managesuppliers")
	public ModelAndView adminClickedSuppliers() {
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isAdminClickedManageSuppliers",true);
		List<Supplier> suppliers=supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		
		return mv;
	}
	
	@GetMapping("/manageproducts")
	public ModelAndView adminClickedProducts() {
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isAdminClickedManageProducts",true);
		//we suppose to fetch all categories and suppliers and set to httpSession
		List<Category> categories =categoryDAO.list();
		List<Supplier> suppliers =supplierDAO.list();
		List<Product> products =productDAO.list();
		
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		httpSession.setAttribute("products", products);
		return mv;
	}



}
