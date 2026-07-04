package com.umfrancisco.app.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@Valid @RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<>(service.saveAccount(accountDTO), HttpStatus.OK);
	}
	
}
