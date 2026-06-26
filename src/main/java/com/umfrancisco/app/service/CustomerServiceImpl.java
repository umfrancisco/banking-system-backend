package com.umfrancisco.app.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository repository;
	private ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<Customer> customers = repository.findAll();
		if (customers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customers not found");
		}
		List<CustomerDTO> customerDTOS = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDTO.class))
				.toList();
		return customerDTOS;
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Customer customerFromDB = repository.findByEmail(customer.getEmail());
		if (customerFromDB != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer "+customer.getCustomerId()+" already exists!");
		}
		var savedCustomer = repository.save(customer);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}
	
}
