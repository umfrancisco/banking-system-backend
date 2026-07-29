package com.umfrancisco.app.service;

import java.util.List;
import com.umfrancisco.app.dto.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> findAllCustomers();
	CustomerDTO findById(Long customerId);
	CustomerDTO findByEmail(String email);
	CustomerDTO saveCustomer(CustomerDTO customerDTO);
	CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);
	CustomerDTO deleteCustomer(Long customerId);
}
