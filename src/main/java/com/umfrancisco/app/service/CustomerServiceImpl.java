package com.umfrancisco.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customers not found");
		}
		List<CustomerDTO> customerDTOS = customers.stream()
				.map(customer -> map(customer))
				.toList();
		return customerDTOS;
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = map(customerDTO);
		Optional<Customer> customerFromDB = customerRepository.findById(customer.getCustomerId());
		if (customerFromDB.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer "+customer.getCustomerId()+" already exists!");
		}
		var savedCustomer = customerRepository.save(customerFromDB.get());
		return map(savedCustomer);
	}
	
	public CustomerDTO map(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhoneNumber(customer.getPhoneNumber());
		customerDTO.setAddress(customer.getAddress());
		return customerDTO;
	}
	
	public Customer map(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setLastName(customerDTO.getLastName());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhoneNumber(customerDTO.getPhoneNumber());
		customer.setAddress(customerDTO.getAddress());
		return customer;
	}
	
}
