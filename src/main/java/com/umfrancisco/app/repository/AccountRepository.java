package com.umfrancisco.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByCustomer(Customer customer);
}
