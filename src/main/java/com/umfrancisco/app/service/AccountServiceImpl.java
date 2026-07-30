package com.umfrancisco.app.service;

import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
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

	@Override
	public List<AccountDTO> findAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		if (accounts.isEmpty()) {
			throw new ResourceNotFoundException("Accounts not found");
		}
		List<AccountDTO> accountDTOS = accounts.stream()
				.map(account -> mapToDTO(account))
				.toList();
		return accountDTOS;
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
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		if (isExistingAccount(customer, accountDTO)) {
			throw new ResourceNotFoundException("This account already exists!");
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
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
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
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		accountRepository.delete(existingAccount);
		return mapToDTO(existingAccount);
	}

}
