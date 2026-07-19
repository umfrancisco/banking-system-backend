package com.umfrancisco.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.service.CustomerService;

@SpringBootTest
class BankingSystemApiApplicationTests {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ModelMapper modelMapper;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void shouldSaveCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@email.com");
		customer.setPhoneNumber("123456789");
		customer.setAddress("Street 123");
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		customerService.saveCustomer(customerDTO);
		CustomerDTO savedCustomer = customerService.findByEmail("john.doe@email.com");
		Assertions.assertNotNull(savedCustomer);
	}

}
