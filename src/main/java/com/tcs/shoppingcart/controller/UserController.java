package com.tcs.shoppingcart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {
	
	//userDAO--backend project
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	HttpSession httpSession;
	//will send usrid and password from jsp to controller
	//it shud validate the credentials
	//it shud return username----valid credentials
	//it shud return error message--invalid credentials
	
	@PostMapping("validate")
	public ModelAndView validate(@RequestParam("uname")String username,@RequestParam("psw")String password) {
		
		ModelAndView mv=new ModelAndView("home");
		user=userDAO.validate(username, password);
		if(user==null) {
			
			//invalid credentials 
			mv.addObject("errorMessage","Inavalid credentials pls try again");
		}
		else {
			//valid credentials
			//mv.addObject("welcomeMessage","Welcom Mr/Mrs"+user.getName());
			httpSession.setAttribute("welcomeMessage","Welcom Mr/Mrs"+user.getName());
			if(user.getRole()=='A') {
				httpSession.setAttribute("isAdmin", true);
			}

		}
		return mv;
	}


}
