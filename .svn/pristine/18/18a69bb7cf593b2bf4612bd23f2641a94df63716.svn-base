package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Apply;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Integer> {


	@Query("select a from Apply a join a.offer o where o.caretaker.id = ?1 order by a.status")
	Collection<Apply> findAllByCaretakerId(int id);


	@Query("select a from Apply a where a.customer.id = ?1")
	Collection<Apply> findAllByCustomer(int id);


}
