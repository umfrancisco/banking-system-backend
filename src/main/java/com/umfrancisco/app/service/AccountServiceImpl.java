package com.umfrancisco.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.umfrancisco.app.dto.AccountDTO;
import com.umfrancisco.app.dto.CustomerDTO;
import com.umfrancisco.app.exception.ResourceNotFoundException;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;
import com.umfrancisco.app.model.enums.AccountStatus;
import com.umfrancisco.app.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private final AccountRepository accountRepository;
	private final CustomerService customerService;
	private ModelMapper modelMapper;
	
	public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService, ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}
	
	private AccountDTO mapToDTO(Account account) {
		return modelMapper.map(account, AccountDTO.class);
	}
	private Account mapToEntity(AccountDTO accountDTO) {
		return modelMapper.map(accountDTO, Account.class);
	}
	private Customer mapToEntity(CustomerDTO customerDTO) {
		return modelMapper.map(customerDTO, Customer.class);
	}

	@Override
	public List<AccountDTO> findAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		if (accounts.isEmpty()) {
			log.error("Accounts not found: {}", accounts);
			throw new ResourceNotFoundException("Accounts not found");
		}
		List<AccountDTO> accountDTOS = accounts.stream()
				.map(account -> mapToDTO(account))
				.toList();
		return accountDTOS;
	}
	
	@Override
	public List<AccountDTO> findByEmail(String email) {
		CustomerDTO existingCustomer = customerService.findByEmail(email);
		Customer customer = mapToEntity(existingCustomer);
		List<Account> existingAccounts = accountRepository.findByCustomer(customer);
		List<AccountDTO> accounts = new ArrayList<>();
		for (var acc : existingAccounts) {
			accounts.add(mapToDTO(acc));
		}
		if (accounts.isEmpty()) {
			log.error("Accounts for this customer not found: {}", existingCustomer);
		}
		return accounts;
	}
	
	private boolean isExistingAccount(Customer customer, AccountDTO accountDTO) {
		List<Account> accountsFromDB = accountRepository.findByCustomer(customer);
		for (var acc : accountsFromDB) {
			if (accountsFromDB != null && acc.getType().equals(accountDTO.getType())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) {
		Account account = mapToEntity(accountDTO);
		CustomerDTO customerDTO = customerService.findById(accountDTO.getCustomerId());
		Customer customer = mapToEntity(customerDTO);
		if (isExistingAccount(customer, accountDTO)) {
			log.error("This account already exists: {}", accountDTO);
			throw new ResourceNotFoundException("This account already exists");
		}
		account.setCustomer(customer);
		account.setCreatedAt(LocalDateTime.now());
		account.setStatus(AccountStatus.ACTIVE);
		var savedAccount = accountRepository.save(account);
		return mapToDTO(savedAccount);
	}

	@Override
	public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
		Account existingAccount = accountRepository.findById(accountId)
				.orElseThrow(() -> {
					log.error("Account not found: {}", accountDTO);
					return new ResourceNotFoundException("Account not found");
				});
		Account account = mapToEntity(accountDTO);
		existingAccount.setCustomer(account.getCustomer());
		existingAccount.setBalance(account.getBalance());
		existingAccount.setType(account.getType());
		existingAccount.setStatus(account.getStatus());
		Account updatedAccount = accountRepository.save(existingAccount);
		return mapToDTO(updatedAccount);
	}

	@Override
	public AccountDTO deleteAccount(Long accountId) {
		Account existingAccount = accountRepository.findById(accountId)
				.orElseThrow(() -> {
					log.error("Account not found: {}", accountId);
					return new ResourceNotFoundException("Account not found");
				});
		accountRepository.delete(existingAccount);
		return mapToDTO(existingAccount);
	}

}
