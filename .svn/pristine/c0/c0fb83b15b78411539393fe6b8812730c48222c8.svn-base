
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

	@Query("select s from Shift s where s.lesson.id = ?1")
	Collection<Shift> findAllByLessonId(int id);
}
