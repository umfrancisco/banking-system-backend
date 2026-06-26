package com.umfrancisco.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.umfrancisco.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByCustomerFirstName(String customerFirstName);
	List<Account> findByCustomerLastName(String customerLastName);
}
