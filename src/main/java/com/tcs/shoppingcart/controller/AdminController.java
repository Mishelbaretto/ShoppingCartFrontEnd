package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger log = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/managecategories")
	public ModelAndView adminClickedCategories() {
		ModelAndView mv = new ModelAndView("home");

		// 1- check wheteher user is logged in or not if not logged in
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if (loggedInUserID == null) {
			mv.addObject("errorMessage", "Please login do to this operation");
			return mv;

		}
		// "pls log in using this operation

		// 2- the already logged in , check what is the role of the user
		Boolean isAdmin = (Boolean) httpSession.getAttribute("isAdmin");

		// if the user is not admin
		// "you are noy authorized to do this operation
		if (isAdmin == null || isAdmin == false) {
			mv.addObject("errorMessage", "You are not authorized to do this operation");
			return mv;
		}
		log.debug("Starting of the method adminClickedCategories");

		mv.addObject("isAdminClickedManageCategories", true);
		List<Category> categories = categoryDAO.list();
		// and set to http session.
		httpSession.setAttribute("categories", categories);
		log.debug("Ending of the method adminClickedCategories");

		return mv;
	}

	@GetMapping("/managesuppliers")
	public ModelAndView adminClickedSuppliers() {
		log.debug("Start of the method AdminClickedSuppliers");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageSuppliers", true);
		List<Supplier> suppliers = supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		log.debug("End of the method AdminClickedSuppliers");

		return mv;
	}

	@GetMapping("/manageproducts")
	public ModelAndView adminClickedProducts() {
		log.debug("Start of the method AdminClickedProducts");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageProducts", true);
		// we suppose to fetch all categories and suppliers and set to httpSession
		List<Category> categories = categoryDAO.list();
		List<Supplier> suppliers = supplierDAO.list();
		List<Product> products = productDAO.list();

		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		httpSession.setAttribute("products", products);
		log.debug("End of the method AdminClickedProducts");
		return mv;
	}

}
