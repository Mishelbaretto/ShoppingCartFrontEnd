package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {
	
	//userDAO--backend project
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	HttpSession httpSession;
	Logger log= LoggerFactory.getLogger(UserController.class);
	
	//will send usrid and password from jsp to controller
	//it shud validate the credentials
	//it shud return username----valid credentials
	//it shud return error message--invalid credentials
	
	@PostMapping("validate")
	public ModelAndView validate(@RequestParam("uname")String username,@RequestParam("psw")String password) {
		
		log.debug("Starting of the validate method");
		ModelAndView mv=new ModelAndView("home");
		user=userDAO.validate(username, password);
		if(user==null) {
			
			//invalid credentials 
			mv.addObject("errorMessage","Inavalid credentials pls try again");
		}
		else {
			//valid credentials
			//mv.addObject("welcomeMessage","Welcom Mr/Mrs"+user.getName());
			httpSession.setAttribute("welcomeMessage","Welcome Mr/Mrs"+user.getName());
			httpSession.setAttribute("loggedInUserID", user.getEmailID());
			httpSession.setAttribute("isLoggedIn", true);
			
			//fetch how many products are sdded to the cart
			
		List<Cart> carts=	cartDAO.list(user.getEmailID());
		httpSession.setAttribute("size", carts.size());
		
		
			if(user.getRole()=='A') {
				httpSession.setAttribute("isAdmin", true);
			}

		}
		log.debug("Ending of the validate method");
		return mv;
	}

	
	@PostMapping("from_form")
	public ModelAndView from_form(@RequestParam("emailID") String emailID, @RequestParam("password") String password, 
								  @RequestParam("mobile") String mobile, @RequestParam("name") String name)
	{
		log.debug("Starting of the from_form method");
		
		ModelAndView mv= new ModelAndView("home");
		
		user.setEmailID(emailID);
		user.setMobile(mobile);
		user.setName(name);
		user.setPassword(password);
		

		userDAO.save(user);
		
		httpSession.setAttribute("success", "Welcome "+ user.getName()+ ". You have successfully created an account with us ");
		httpSession.setAttribute("Registered", true);
		mv.addObject("sinceUserClickedLogin", true);

		log.debug("End of the from_form method");
		return mv;
	}

}
