package com.verizon.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.verizon.customer.entity.Customer;
import com.verizon.customer.entity.CustomerAddress;
import com.verizon.customer.model.CustomerDetails;
import com.verizon.customer.service.CustomerService;

import brave.Tracer;



@RestController
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${kafka.service.uri}")
	private String kafkaServiceUri;
	
	@Autowired
	Tracer tracer;
	
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
		Long currentTime = System.currentTimeMillis();
		LOGGER.info("Calling Customer Service for customer id :  {}",id);
		CustomerDetails customerdetails = customerService.getCustomerDetailsById(id);
		String message = String.format("%d | %s | %s | %s | %s | %s", currentTime, "CustomerService", tracer.currentSpan().context().spanIdString(), tracer.currentSpan().context().traceIdString(), id, customerdetails);
		restTemplate.getForObject(kafkaServiceUri + "=" + message, String.class);
		return customerdetails;
	}
}
