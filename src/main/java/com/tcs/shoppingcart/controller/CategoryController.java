package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;
@Controller
public class CategoryController {

	//we need to call categorydao methods
	//get, save, update, delete, list
	
	//1.inject the categorydao and categorydomain objects
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired HttpSession httpSession;
	//http://localhost:8080/shoppingcart/category/get/cate_001
	//@GetMapping("/category/get{id}")
	
	@RequestMapping(name="/category/get/{id}" , method=RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id") String id) {
		//based on id,fetch the details from categorydao
	category=	categoryDAO.get(id);
	//navigate to homepage
	ModelAndView mv= new ModelAndView("home");
	mv.addObject("category", category);
	return mv;
		
	}
	@PostMapping("/category/save/")
	/*public ModelAndView saveCategory(@RequestParam("id")String id,
			@RequestParam("id")String name,
			@RequestParam("id")String description)*/
	
	public ModelAndView saveCategory(@RequestParam("id")String id,
			@RequestParam("name")String name,
			@RequestParam("description")String description) {
		ModelAndView mv=new ModelAndView("redirect:/managecategories");
		
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		//navigate to homepage
	/*	ModelAndView mv= new ModelAndView("home");
		//call save method of category
		if(categoryDAO.save(category)==true) {
			//add success
			mv.addObject("successMessage","The category saved sucessfully");
		}
		else
		{
			//add failure message
			mv.addObject("errorMessage","could not save the category");
		}
		return mv;*/
		if(categoryDAO.save(category)){
			mv.addObject("successMessage","The Category created successfully");
			//fetch all the categries again
	//	List<Category> categories=	categoryDAO.list();
			// and set to http session
	//	httpSession.setAttribute("categories", categories);
		}else
		{
			mv.addObject("errorMessage","could not create successfully ");
		}
		return mv;
			
	}
	
	
	@PutMapping("/category/update/")
public ModelAndView updateCategory(@ModelAttribute Category category) {
		
		//navigate to homepage
		ModelAndView mv= new ModelAndView("home");
		//call save method of category
		if(categoryDAO.update(category)==true) {
			//add success
			mv.addObject("categorySuccessMessage","The category updated sucessfully");
		}
		else
		{
			//add failure message
			mv.addObject("categoryErrorMessage","could not updated the category");
		}
		return mv;
	}
	
	@GetMapping("/category/delete")
		//@RequestMapping(name="/category/get/{id}" , method=RequestMethod.GET)
		public ModelAndView deleteCategory(@RequestParam("id")String id) {
		
		//navigate to homepage
				ModelAndView mv= new ModelAndView("redirect:/managecategories");
				
				
				mv.addObject("category", category);
			//based on id,fetch the details from categorydao
		if(	categoryDAO.delete(id)==true) {
			mv.addObject("successMessage","The category deleted sucessfully");
		}
		else
		{
			mv.addObject("errorMessage","could not delete the category");
		}
		
		return mv;
			
		}
	
	@GetMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam String id) {
		ModelAndView mv=new ModelAndView("redirect:/managecategories");
		//based on category id and fetch details
		category=categoryDAO.get(id);
	//	mv.addObject("selectedCategory",category);
		httpSession.setAttribute("selectedCategory", category);
		return mv;
	}
	
	
	
	
	
	
	
	@GetMapping("/categories")
	public ModelAndView getAllCategories() {
		ModelAndView mv=new ModelAndView("home");
	List<Category>	categories= categoryDAO.list();
	mv.addObject("categories",categories);
	return mv;
	
	}

}
