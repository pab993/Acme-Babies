
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Kindergarten;

public interface KindergartenRepository extends JpaRepository<Kindergarten, Integer> {

	@Query("select k from Kindergarten k where k.userAccount.id = ?1")
	Kindergarten findByUserAccountId(int userAccountId);

	@Query("select k from Kindergarten k where k.idNumber = ?1")
	Collection<Kindergarten> findByIdNumber(String idNumber);

	@Query("select 1 from Actor a where a.userAccount.username = ?1")
	Integer checkKindergartenByUsername(String username);
	
	@Query("select k from Kindergarten k join k.lessons l where l.inscriptions.size >= (select max(l1.inscriptions.size) from Lesson l1)")
	Collection<Kindergarten> kindergartenWithMoreCustomersInLessons();
	
	@Query("select k from Kindergarten k join k.lessons l where l.inscriptions.size >= (select min(l1.inscriptions.size) from Lesson l1)")
	Collection<Kindergarten> kindergartenWithLessCustomersInLessons();
	
	@Query("select k from Kindergarten k order by k.lessons.size DESC")
	Collection<Kindergarten> rankingKindergartensCreatedLessons();


}
