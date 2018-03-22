package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class AdminController {
	
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;
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
	
	@GetMapping("/managesupplier")
	public ModelAndView adminClickedSuppliers() {
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isAdminClickedManageSuppliers",true);
		return mv;
	}
	
	@GetMapping("/manageproducts")
	public ModelAndView adminClickedProducts() {
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isAdminClickedManageProducts",true);
		return mv;
	}



}
