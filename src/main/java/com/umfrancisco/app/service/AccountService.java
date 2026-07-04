package com.umfrancisco.app.service;

import java.util.List;
import com.umfrancisco.app.dto.AccountDTO;

public interface AccountService {
	List<AccountDTO> findAllAccounts();
	AccountDTO saveAccount(AccountDTO accountDTO);
}
