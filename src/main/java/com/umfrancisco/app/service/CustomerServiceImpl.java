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
	
	private CustomerDTO mapToDTO(Customer customer) {
		return modelMapper.map(customer, CustomerDTO.class);
	}
	
	private Customer mapToEntity(CustomerDTO customerDTO) {
		return modelMapper.map(customerDTO, Customer.class);
	}
	
	@Override
	public List<CustomerDTO> findAllCustomers() {
		List<Customer> customers = repository.findAll();
		if (customers.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customers not found");
		}
		List<CustomerDTO> customerDTOS = customers.stream()
				.map(customer -> mapToDTO(customer))
				.toList();
		return customerDTOS;
	}
	
	@Override
	public CustomerDTO findByEmail(String email) {
		Customer customer = repository.findByEmail(email);
		if (customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
		}
		return mapToDTO(customer);
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = mapToEntity(customerDTO);
		Customer customerFromDB = repository.findByEmail(customer.getEmail());
		if (customerFromDB != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer "+customer.getCustomerId()+" already exists!");
		}
		var savedCustomer = repository.save(customer);
		return mapToDTO(savedCustomer);
	}
	
}
