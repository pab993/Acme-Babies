
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Customer;
import domain.Kindergarten;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);

	@Query("select 1 from Actor a where a.userAccount.username = ?1")
	Integer checkCustomerByUsername(String username);

	@Query("select ins.customer from Lesson l join l.inscriptions ins where l.kindergarten.id = 13")
	Collection<Customer> findAllByKindergarten(Kindergarten kindergarten);

}
