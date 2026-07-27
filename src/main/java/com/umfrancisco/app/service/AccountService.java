package com.umfrancisco.app.service;

import java.util.List;
import com.umfrancisco.app.dto.AccountDTO;

public interface AccountService {
	List<AccountDTO> findAllAccounts();
	AccountDTO saveAccount(AccountDTO accountDTO);
	AccountDTO updateAccount(Long accountId, AccountDTO accountDTO);
	AccountDTO deleteAccount(Long accountId);
}
