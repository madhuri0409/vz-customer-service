package com.verizon.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.customer.entity.Customer;
import com.verizon.customer.entity.CustomerAddress;
import com.verizon.customer.model.CustomerDetails;
import com.verizon.customer.service.CustomerService;



@RestController
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/customersAddress")
	public List<CustomerAddress> getCustomerAddress(){
		return customerService.getAllCustomersAddress();
	}
	
	@GetMapping("/customerById")
	public CustomerDetails getCustomerById(@RequestParam(value = "id") Integer id) {
		LOGGER.info("Calling Customer Service for customer id :  {}",id);
		return customerService.getCustomerDetailsById(id);
	}
}
