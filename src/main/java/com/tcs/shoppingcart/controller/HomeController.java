package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired
	private HttpSession httpSession;
	
	
	//http://localhost:8080/ShoppingcartFrontEnd/
	/*@RequestMapping("/")
	public String navigateToHome() {
		return "Welcome to home page";
	}*/
	//http://localhost:8080/ShoppingCartFrontEnd/
	@GetMapping("/")
	public ModelAndView  home() {
		ModelAndView mv=new ModelAndView("home");
		//add the data to mv
		//we need to fetch all the categories 
		//first we need to autowire CategoryDAO and category
	List<Category>  categories =	categoryDAO.list();
		//add the data to mv
		//mv.addObject("categories", "categories");
	httpSession.setAttribute("categories", categories);
		
		return mv;
	}
	//http://localhost:8080/ShoppingCartFrontEnd/login
		@GetMapping("/login")
		public ModelAndView  login() {
			ModelAndView mv=new ModelAndView("home");
			
			mv.addObject("isUserClickedLogin", true);
			return mv;
		}
		@GetMapping("/logout")
		public ModelAndView  logout() {
			ModelAndView mv=new ModelAndView("home");
			httpSession.invalidate();
			mv.addObject("logoutMessage", "You are successfully logged out");
			return mv;
		}
		
		

		//http://localhost:8080/ShoppingCartFrontEnd/register
		@GetMapping("/register")
		public ModelAndView  registration() {
			ModelAndView mv=new ModelAndView("home");
			mv.addObject("isUserClickedRegister", true);
			return mv;
		}


}
