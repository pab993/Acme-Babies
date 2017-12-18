
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {


	@Query("select i from Invoice i join i.inscription ins where ins.customer.id = ?1")
	Collection<Invoice> findAllByActorId(int id);



}
