package com.tcs.shoppingcart.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
	//private static String imageDirectory="resources"+File.separator+"images";
	//private static String rootPath="resources//images"
	//"G:\\eclipse oxy\\391\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\images";
	private static String imageDirectory = "G:\\eclipse oxy\\391\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\images";

	

	
	
	//http://localhost:8080/ShoppingcartFrontEnd/
	/*@RequestMapping("/")
	public String navigateToHome() {
		return "Welcome to home page";
	}*/
	//http://localhost:8080/ShoppingCartFrontEnd/
	@GetMapping("/")
	public ModelAndView  home(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("home");
		//add the data to mv
		//we need to fetch all the categories 
		//first we need to autowire CategoryDAO and category
	List<Category>  categories =	categoryDAO.list();
		//add the data to mv
		//mv.addObject("categories", "categories");
	httpSession.setAttribute("categories", categories);
	httpSession.setAttribute("imageDirectory", imageDirectory);
	String root =request.getContextPath();
    String imageFolder =  root + File.separator +"src" + File.separator + 
    		"main" +File.separator +
    		"webapp"+File.separator +
    		"resources"+File.separator;	
    httpSession.setAttribute("imageFolder", imageFolder);
	
		
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
			httpSession.invalidate();//removes all the attribute which are added
			
		/*	httpSession.removeAttribute("loggedInUserID");
			httpSession.removeAttribute("isLoggedIn");
			httpSession.removeAttribute("isLoggedAdmin");*/
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
