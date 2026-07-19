package com.umfrancisco.app;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.umfrancisco.app.dto.AccountDTO;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.model.enums.AccountType;
import com.umfrancisco.app.service.AccountService;
import com.umfrancisco.app.service.CustomerService;

import jakarta.transaction.Transactional;

@SpringBootTest
class BankingSystemApiApplicationTests {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void shouldSaveCustomer() {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("john.doe@email.com");
		customer.setPhoneNumber("123456789");
		customer.setAddress("Street 123");
		
		CustomerDTO savedCustomer = customerService.saveCustomer(customer);
		Assertions.assertNotNull(savedCustomer);
		Assertions.assertEquals("john.doe@email.com", savedCustomer.getEmail());
	}
	
	@Test
	void shouldSaveAccount() {
		CustomerDTO customer = customerService.findByEmail("john.doe@email.com");
		Assertions.assertNotNull(customer);
		
		AccountDTO account = new AccountDTO();
		account.setCustomerEmail(customer.getEmail());
		account.setBalance(BigDecimal.valueOf(1000.0));
		account.setType(AccountType.CHECKING);
		AccountDTO savedAccount = accountService.saveAccount(account);
		
		Assertions.assertNotNull(savedAccount);
	}

}
