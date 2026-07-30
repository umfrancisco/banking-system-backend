package com.umfrancisco.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.umfrancisco.app.model.Account;
import com.umfrancisco.app.model.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByCustomer(Customer customer);
}

//class AccountJdbcRepository {
//	
//	private static final Logger log = LoggerFactory.getLogger(AccountRepository.class);
//	private final JdbcClient jdbcClient;
//	
//	public AccountJdbcRepository(JdbcClient jdbcClient) {
//		this.jdbcClient = jdbcClient;
//	}
//	
//	public List<Account> findAll() {
//		 return jdbcClient.sql("select * from account")
//		        .query((rs, rowNum) -> {
//		            Account account = new Account();
//		            account.setAccountId(rs.getLong("account_id"));
//		            Customer customer = new Customer();
//		            customer.setCustomerId(rs.getLong("customer_id"));
//		            account.setCustomer(customer);
//		            account.setBalance(rs.getBigDecimal("balance"));
//		            account.setType(AccountType.valueOf(rs.getString("type")));
//		            account.setStatus(AccountStatus.valueOf(rs.getString("status")));
//		            account.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
//		            return account;
//		        })
//		        .list();
//	}
//	
//	public Optional<Account> findById(Long accountId) {
//		String query = """
//				select * from account where account_id = :id
//				""";
//		return jdbcClient.sql(query)
//				.param("id", accountId)
//				.query(Account.class)
//				.optional();
//	}
//	
//	public List<Account> findByCustomer(Customer customer) {
//		String query = """
//				select * from account where customer_id = :customer_id
//				""";
//		return jdbcClient.sql(query)
//				.param("customer_id", customer.getCustomerId())
//				.query(Account.class)
//				.list();
//	}
//	
//	public Account save(Account a) {
//		String query = """
//				insert into account(customer_id, balance, type, status, created_at) 
//				values(?,?,?,?,?)
//				""";
//		int rowsUpdated = jdbcClient.sql(query)
//				.params(List.of(
//						a.getCustomer().getCustomerId(),
//						a.getBalance(), 
//						a.getType().name(),
//						a.getStatus().name(),
//						a.getCreatedAt()))
//				.update();
//		
//		if (rowsUpdated != 1) {
//			var err = "Error while saving account";
//			log.error(err);
//			throw new ApiException(err);
//		}
//		log.info("New account created: "+a);
//	    return a;
//	}
//	
//	public void delete(Account account) {
//		
//	}
//}
