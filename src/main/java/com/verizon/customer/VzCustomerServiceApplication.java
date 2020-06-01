package com.verizon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VzCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VzCustomerServiceApplication.class, args);
	}

}
