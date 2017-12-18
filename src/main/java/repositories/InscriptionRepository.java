
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

	@Query("select count(c) from Customer c join c.inscriptions i where i.lesson.id=?1 and c.id=?2")
	Integer countIncriptionByLessorForCustomer(int idLessor, int idCustomer);

	@Query("select i from Inscription i where i.customer.id = ?1")
	Collection<Inscription> findAllByCustomer(int id);

}
