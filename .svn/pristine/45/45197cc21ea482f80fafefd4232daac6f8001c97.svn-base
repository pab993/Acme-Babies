
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import domain.Actor;
import domain.Caretaker;
import domain.Offer;

@Service
@Transactional
public class OfferService {

	//Managed Repository =============================================================================

	@Autowired
	private OfferRepository		offerRepository;

	@Autowired
	private CaretakerService	caretakerService;

	@Autowired
	private ActorService		actorService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Offer findOne(final int offerId) {
		Assert.notNull(offerId);

		Offer result;
		result = this.offerRepository.findOne(offerId);

		return result;
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = this.offerRepository.findAll();

		return result;
	}

	public Offer create() {
		Offer result;
		Caretaker principal;
		final long l = 10;
		final Date createMoment = new Date(System.currentTimeMillis() - l);

		principal = this.caretakerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Caretaker.class, principal);

		result = new Offer();
		result.setCaretaker(principal);
		result.setCreateMoment(createMoment);

		return result;
	}

	public Offer save(final Offer offer) {
		Assert.notNull(offer);
		Offer result;
		Caretaker principal;

		principal = this.caretakerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(offer.getCaretaker().equals(principal));
		Assert.isTrue(this.actorService.checkCreditCard(principal));
		result = this.offerRepository.save(offer);

		return result;
	}
	public void delete(final Offer offer) {
		Assert.notNull(offer);
		Caretaker principal;

		principal = this.caretakerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(offer.getCaretaker()));
		Assert.isInstanceOf(Caretaker.class, principal);

		Assert.isTrue(offer.getApplies().isEmpty());

		this.offerRepository.delete(offer);
	}

	public Collection<Offer> getOfferByKeyWord(final String keyWord) {
		Assert.notNull(keyWord);
		Collection<Offer> result;

		result = this.offerRepository.searchOfferByWords(keyWord);

		return result;
	}

	public Collection<Offer> findAllByCustomer(Actor actor) {
		Assert.notNull(actor);
		Collection<Offer> result;

		result = this.offerRepository.findAllByCustomerId(actor.getId());

		return result;
	}

	public Collection<Offer> findAllByCaretaker(final Actor actor) {
		Assert.notNull(actor);
		Collection<Offer> result;

		result = this.offerRepository.findAllByCaretakerId(actor.getId());

		return result;
	}
	
}
