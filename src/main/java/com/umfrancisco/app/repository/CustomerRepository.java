package com.umfrancisco.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.umfrancisco.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query(nativeQuery=true, value="SELECT * FROM customer ORDER BY last_name ASC")
	List<Customer> findAll();
	@Query(nativeQuery=true, value="select * from customer where email=:email")
	Optional<Customer> findByEmail(@Param("email") String email);
}

//class CustomerJdbcRepository {
//	
//	private static final Logger log = LoggerFactory.getLogger(CustomerJdbcRepository.class);
//	private final JdbcClient jdbcClient;
//	
//	public CustomerJdbcRepository(JdbcClient jdbcClient) {
//		this.jdbcClient = jdbcClient;
//	}
//	
//	public List<Customer> findAll() {
//		return jdbcClient.sql("select * from customer")
//				.query(Customer.class)
//				.list();
//	}
//	
//	public Optional<Customer> findById(Long customerId) {
//		String query = """
//				select * from customer where customer_id = :id
//				""";
//		return jdbcClient.sql(query)
//				.param("id", customerId)
//				.query(Customer.class)
//				.optional();
//	}
//	
//	public Optional<Customer> findByEmail(String email) {
//		String query = """
//				select * from customer where email = :email
//				""";
//		return jdbcClient.sql(query)
//				.param("email", email)
//				.query(Customer.class)
//				.optional();
//				
//	}
//	
//	public Customer save(Customer c) {
//		String query = """
//				insert into customer(first_name, last_name, email, phone_number, address) 
//				values(?,?,?,?,?)
//				""";
//		int rowsUpdated = jdbcClient.sql(query)
//				.params(List.of(c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhoneNumber(), c.getAddress()))
//				.update();
//		
//		if (rowsUpdated != 1) {
//			var err = "Error while saving customer";
//			log.error(err);
//			throw new ApiException(err);
//		}
//		log.info("New customer created: "+c);
//	    return c;
//	}
//	
//	public void delete(Customer customer) {
//		
//	}
//}
