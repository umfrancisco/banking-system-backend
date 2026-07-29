package com.umfrancisco.app.repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import com.umfrancisco.app.exception.ApiException;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;

@Repository
public class AccountRepository {
	
	private static final Logger log = LoggerFactory.getLogger(AccountRepository.class);
	private final JdbcClient jdbcClient;
	
	public AccountRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}
	
	public List<Account> findAll() {
		return jdbcClient.sql("select * from account")
				.query(Account.class)
				.list();
	}
	
	public Optional<Account> findById(Long accountId) {
		String query = """
				select * from customer where id = :id
				""";
		return jdbcClient.sql(query)
				.param("id", accountId)
				.query(Account.class)
				.optional();
	}
	
	public List<Account> findByCustomer(Customer customer) {
		String query = """
				select * from account where customer_id = :customer_id
				""";
		return jdbcClient.sql(query)
				.param("customer_id", customer.getCustomerId())
				.query(Account.class)
				.list();
	}
	
	public Account save(Account a) {
		String query = """
				insert into account(customer_id, balance, type, status, created_at) 
				values(?,?,?,?,?)
				""";
		int rowsUpdated = jdbcClient.sql(query)
				.params(List.of(
						a.getCustomer().getCustomerId(),
						a.getBalance(), 
						a.getType().ordinal(),
						a.getStatus().ordinal(),
						a.getCreatedAt()))
				.update();
		
		if (rowsUpdated != 1) {
			var err = "Error while saving account";
			log.error(err);
			throw new ApiException(err);
		}
		log.info("New account created: "+a);
	    return a;
	}
	
	public void delete(Account account) {
		
	}
}
