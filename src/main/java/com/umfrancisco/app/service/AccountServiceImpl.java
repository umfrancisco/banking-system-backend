package com.umfrancisco.app.service;

import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.umfrancisco.app.dto.AccountDTO;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private final AccountRepository repository;
	private final CustomerService customerService;
	private ModelMapper modelMapper;
	
	public AccountServiceImpl(AccountRepository repository, CustomerService customerService, ModelMapper modelMapper) {
		this.repository = repository;
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<AccountDTO> findAllAccounts() {
		List<Account> accounts = repository.findAll();
		if (accounts.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accounts not found");
		}
		List<AccountDTO> accountDTOS = accounts.stream()
				.map(account -> modelMapper.map(account, AccountDTO.class))
				.toList();
		return accountDTOS;
	}

	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) {
		Account account = modelMapper.map(accountDTO, Account.class);
		CustomerDTO customerDTO = customerService.findByEmail(accountDTO.getCustomerEmail());
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Account accountsFromDB = repository.findByCustomer(customer);
		if (accountsFromDB != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account from "+account.getCustomer().getEmail()+" already exists!");
		}
		account.setCustomer(customer);
		account.setCreatedAt(LocalDateTime.now());
		account.setStatus(AccountStatus.ACTIVE);
		var savedAccount = repository.save(account);
		return modelMapper.map(savedAccount, AccountDTO.class);
	}

}
