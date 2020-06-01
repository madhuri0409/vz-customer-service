package com.verizon.customer.controller;

import java.util.List;

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
		return customerService.getCustomerDetailsById(id);
	}
}
