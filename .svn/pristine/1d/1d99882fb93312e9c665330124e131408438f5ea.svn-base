
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comentable;

@Repository
public interface ComentableRepository extends JpaRepository<Comentable, Integer> {

	@Query("select c from Comentable c where c.id = ?1")
	Comentable findOneAux(int comentableId);
}
