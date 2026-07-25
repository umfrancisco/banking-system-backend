package com.umfrancisco.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.service.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private final CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping("/public/customer")
	public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
		return new ResponseEntity<>(service.findAllCustomers(), HttpStatus.OK);
	}
	
	@PostMapping("/public/customer")
	public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(service.saveCustomer(customerDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/customer/{customerId}")
	public ResponseEntity<CustomerDTO> updateCustomer(@Valid @PathVariable Long customerId, @Valid @RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(service.updateCustomer(customerId, customerDTO), HttpStatus.OK);
	}
	
}
