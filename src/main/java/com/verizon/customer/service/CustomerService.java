package com.verizon.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.customer.entity.Customer;
import com.verizon.customer.entity.CustomerAddress;
import com.verizon.customer.model.CustomerDetails;
import com.verizon.customer.repo.CustomerAddressRepository;
import com.verizon.customer.repo.CustomerRepository;

@Service
public class CustomerService {

	Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public List<CustomerAddress> getAllCustomersAddress() {
		return customerAddressRepository.findAll();
	}

	public CustomerDetails getCustomerDetailsById(Integer customerId) {
		LOGGER.info("In Customer Service : Fetching Customer Details Started for Customer Id {}", customerId);
		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<CustomerAddress> customerAddress = customerAddressRepository.findFirstByCustomerId(customerId);
		CustomerDetails customerDetails = null;
		if (customer.isPresent() && customerAddress.isPresent()) {
			customerDetails = new CustomerDetails();
			Customer customerData = customer.get();
			CustomerAddress customerAddressData = customerAddress.get();
			customerDetails.setCustomerId(customerData.getCustomerId());
			customerDetails.setFirstName(customerData.getFirstName());
			customerDetails.setLastName(customerData.getLastName());
			customerDetails.setStreet(customerAddressData.getStreet());
			customerDetails.setCity(customerAddressData.getCity());
			customerDetails.setState(customerAddressData.getState());
			customerDetails.setZipcode(customerAddressData.getZipcode());
		}
		LOGGER.info(
				"In Customer Service : Fetching Customer Details Complete for Customer Id {} : Customer Details : {}",
				customerId, customerDetails);
		return customerDetails;
	}
}
