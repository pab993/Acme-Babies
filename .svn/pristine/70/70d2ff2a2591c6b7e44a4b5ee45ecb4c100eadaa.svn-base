
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Caretaker;

public interface CaretakerRepository extends JpaRepository<Caretaker, Integer> {

	@Query("select c from Caretaker c where c.userAccount.id = ?1")
	Caretaker findByUserAccountId(int userAccountId);

	@Query("select c from Caretaker c where c.idNumber = ?1")
	Collection<Caretaker> findByIdNumber(String idNumber);

	@Query("select 1 from Actor a where a.userAccount.username = ?1")
	Integer checkCaretakerByUsername(String username);
	
	@Query("select c from Caretaker c where c.assessments.size >= (select max(c1.assessments.size) from Caretaker c1)")
	Collection<Caretaker> caretakerWithMoreAssessments();
	
	@Query("Select c, count(a) from Caretaker c, Apply a where a.status = 'ACCEPTED' and a.offer.caretaker = c group by c order by count(a) DESC) and o.applies.status = 'ACCEPTED'")
	Collection<Object[]> rankingCaretakersAcceptedOffers();
}
