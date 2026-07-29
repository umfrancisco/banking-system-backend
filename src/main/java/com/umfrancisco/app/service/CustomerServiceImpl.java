package com.umfrancisco.app.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.exception.ApiException;
import com.umfrancisco.app.exception.ResourceNotFoundException;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.repository.AccountRepository;
import com.umfrancisco.app.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private final CustomerRepository customerRepository;
	private final AccountRepository accountRepository;
	private ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
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
		List<Customer> customers = customerRepository.findAll();
		if (customers.isEmpty()) {
			log.error("Customer table is empty: "+customers);
			throw new ResourceNotFoundException("Customers not found");
		}
		List<CustomerDTO> customerDTOS = customers.stream()
				.map(customer -> mapToDTO(customer))
				.toList();
		return customerDTOS;
	}
	
	@Override
	public CustomerDTO findByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			log.error("Email not found: "+email);
			throw new ResourceNotFoundException("Customer not found");
		}
		return mapToDTO(customer);
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = mapToEntity(customerDTO);
		Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());
		if (existingCustomer != null) {
			log.error("Cannot save this customer: "+customerDTO);
			throw new ApiException("Customer "+customer.getCustomerId()+" already exists!");
		}
		var savedCustomer = customerRepository.save(customer);
		return mapToDTO(savedCustomer);
	}

	@Override
	public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> {
					log.error("Error while updating customer: "+customerId);
					return new ResourceNotFoundException("Customer with ID "+customerId+" not found");
				});
		// FIELDS: firstName, lastName, email, phoneNumber, address
		Customer customer = mapToEntity(customerDTO);
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPhoneNumber(customer.getPhoneNumber());
		existingCustomer.setAddress(customer.getAddress());
		Customer updatedCustomer = customerRepository.save(existingCustomer);
		return mapToDTO(updatedCustomer);
	}

	@Override
	public CustomerDTO deleteCustomer(Long customerId) {
		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> {
					log.error("Error while deleting customer: "+customerId);
					return new ResourceNotFoundException("Customer with ID "+customerId+" not found");
				});
		List<Account> accountsFromCustomer = accountRepository.findByCustomer(existingCustomer);
		if (accountsFromCustomer.isEmpty()) {
			customerRepository.delete(existingCustomer);
			return mapToDTO(existingCustomer);
		}
		throw new ApiException("Cannot delete customer");
	}
	
}
