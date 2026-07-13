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
	
	private AccountDTO mapToDTO(Account account) {
		return modelMapper.map(account, AccountDTO.class);
	}
	
	private Account mapToEntity(AccountDTO accountDTO) {
		return modelMapper.map(accountDTO, Account.class);
	}

	@Override
	public List<AccountDTO> findAllAccounts() {
		List<Account> accounts = repository.findAll();
		if (accounts.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accounts not found");
		}
		List<AccountDTO> accountDTOS = accounts.stream()
				.map(account -> mapToDTO(account))
				.toList();
		return accountDTOS;
	}

	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) {
		Account account = mapToEntity(accountDTO);
		CustomerDTO customerDTO = customerService.findByEmail(accountDTO.getCustomerEmail());
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		Account accountsFromDB = repository.findByCustomer(customer);
		if (accountsFromDB != null && accountsFromDB.getType().equals(accountDTO.getType())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account from "+account.getCustomer().getEmail()+" already exists!");
		}
		account.setCustomer(customer);
		account.setCreatedAt(LocalDateTime.now());
		account.setStatus(AccountStatus.ACTIVE);
		var savedAccount = repository.save(account);
		return mapToDTO(savedAccount);
	}

}
