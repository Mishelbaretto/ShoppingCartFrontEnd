package com.tcs.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {
	// we need to call supplierdao methods
	// get, save, update, delete, list

	// 1.inject the supplierdao and supplierdomain objects
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Supplier supplier;

	@Autowired
	HttpSession httpSession;

	Logger log = LoggerFactory.getLogger(SupplierController.class);

	// http://localhost:8080/shoppingcart/supplier/get/cate_001
	// @GetMapping("/supplier/get{id}")
	/*
	 * @RequestMapping(name="/supplier/get/{id}" , method=RequestMethod.GET) public
	 * ModelAndView getSupplier(@RequestParam("id") String id) { //based on id,fetch
	 * the details from supplierdao supplier= supplierDAO.get(id); //navigate to
	 * homepage ModelAndView mv= new ModelAndView("home"); mv.addObject("supplier",
	 * supplier); return mv;
	 * 
	 * }
	 */
	@PostMapping("/supplier/save/")
	/*
	 * public ModelAndView saveSupplier(@RequestParam("id")String id,
	 * 
	 * @RequestParam("id")String name,
	 * 
	 * @RequestParam("id")String address)
	 */

	public ModelAndView saveSupplier(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("address") String address) {

		log.debug("Start of the supplier save method");
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");

		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);
		// navigate to homepage
		/*
		 * ModelAndView mv= new ModelAndView("home"); //call save method of supplier
		 * if(supplierDAO.save(supplier)==true) { //add success
		 * mv.addObject("successMessage","The supplier saved sucessfully"); } else {
		 * //add failure message
		 * mv.addObject("errorMessage","could not save the supplier"); } return mv;
		 */
		if (supplierDAO.save(supplier)) {
			mv.addObject("supplierSuccessMessage", "The Supplier created successfully");
			// fetch all the categries again
			List<Supplier> suppliers = supplierDAO.list();
			// and set to http session
			httpSession.setAttribute("suppliers", suppliers);
		} else {
			mv.addObject("supplierErrorMessage", "could not create successfully ");
		}
		log.debug("End of the supplier save method");
		return mv;

	}

	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {

		log.debug("Start of the supplier update method");
		// navigate to homepage
		ModelAndView mv = new ModelAndView("home");
		// call save method of supplier
		if (supplierDAO.update(supplier) == true) {
			// add success
			mv.addObject("supplierSuccessMessage", "The supplier updated sucessfully");
		} else {
			// add failure message
			mv.addObject("supplierErrorMessage", "could not updated the supplier");
		}
		log.debug("Start of the supplier update method");
		return mv;
	}

	@GetMapping("/supplier/delete/")
	// @RequestMapping(name="/supplier/get/{id}" , method=RequestMethod.GET)
	public ModelAndView deleteSupplier(@RequestParam String id) {

		log.debug("Start of the supplier delete method");
		// navigate to homepage
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");

		// mv.addObject("supplier", supplier);
		// based on id,fetch the details from supplierdao
		if (supplierDAO.delete(id) == true) {
			mv.addObject("supplierSuccessMessage", "The supplier deleted sucessfully");
		} else {
			mv.addObject("supplierErrorMessage", "could not delete the supplier");
		}
		log.debug("Start of the supplier delete method");
		return mv;

	}

	@GetMapping("/supplier/edit/")
	public ModelAndView editSupplier(@RequestParam String id) {

		log.debug("Start of the supplier edit  method");
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		// based on supplier id and fetch details
		supplier = supplierDAO.get(id);
		// mv.addObject("selectedSupplier",supplier);
		httpSession.setAttribute("selectedSupplier", supplier);
		log.debug("Start of the supplier edit method");
		return mv;
	}

	@GetMapping("/suppliers")
	public ModelAndView getAllSuppliers() {
		ModelAndView mv = new ModelAndView("home");
		List<Supplier> suppliers = supplierDAO.list();
		mv.addObject("suppliers", suppliers);
		return mv;

	}

}
