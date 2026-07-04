package com.umfrancisco.app.service;

import java.util.List;
import com.umfrancisco.app.dto.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> findAllCustomers();
	CustomerDTO findByEmail(String email);
	CustomerDTO saveCustomer(CustomerDTO customerDTO);
}
