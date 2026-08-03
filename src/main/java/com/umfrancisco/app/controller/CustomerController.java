package com.umfrancisco.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
		return new ResponseEntity<>(service.findAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<CustomerDTO> findCustomerByEmail(@Valid @PathVariable String email) {
		return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(service.saveCustomer(customerDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> updateCustomer(@Valid @PathVariable Long customerId, @Valid @RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(service.updateCustomer(customerId, customerDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> deleteCustomer(@Valid @PathVariable Long customerId) {
		return new ResponseEntity<>(service.deleteCustomer(customerId), HttpStatus.OK);
	}
	
}
