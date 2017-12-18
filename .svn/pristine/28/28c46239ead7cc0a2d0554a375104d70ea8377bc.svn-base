package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	
	@Query("select o from Offer o where o.title like %?1% or o.description like %?1% or o.price like %?1%")
	Collection<Offer> searchOfferByWords(String keyWord);

	@Query("select o from Offer o join o.applies a where a.customer.id = ?1")
	Collection<Offer> findAllByCustomerId(int id);

	@Query("select o from Offer o where o.caretaker.id = ?1")
	Collection<Offer> findAllByCaretakerId(int id);

}
