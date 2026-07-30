package com.umfrancisco.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.umfrancisco.app.dto.AccountDTO;
import com.umfrancisco.app.service.AccountService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	private final AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDTO>> findAllAccounts() {
		return new ResponseEntity<>(service.findAllAccounts(), HttpStatus.OK);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<AccountDTO>> findAccountByEmail(@Valid @PathVariable String email) {
		return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@Valid @RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<>(service.saveAccount(accountDTO), HttpStatus.OK);
	}
	
	@PutMapping("/{accountId}")
	public ResponseEntity<AccountDTO> updateAccount(@Valid @PathVariable Long accountId, @Valid @RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<>(service.updateAccount(accountId, accountDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseEntity<AccountDTO> deleteAccount(@Valid @PathVariable Long accountId) {
		return new ResponseEntity<>(service.deleteAccount(accountId), HttpStatus.OK);
	}
	
}
