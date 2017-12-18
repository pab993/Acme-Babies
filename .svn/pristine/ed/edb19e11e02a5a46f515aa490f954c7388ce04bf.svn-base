
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplyRepository;
import domain.Actor;
import domain.Apply;
import domain.Caretaker;
import domain.Customer;
import domain.Offer;

@Service
@Transactional
public class ApplyService {

	//Managed Repository =============================================================================

	@Autowired
	private ApplyRepository	applyRepository;

	@Autowired
	private CustomerService	customerService;

	@Autowired
	private ActorService	actorService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Apply findOne(final int applyId) {
		Assert.notNull(applyId);

		Apply result;
		result = this.applyRepository.findOne(applyId);

		return result;
	}

	public Apply create(final Offer offer) {
		Apply result;
		Customer principal;

		principal = this.customerService.findByPrincipal();

		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);

		result = new Apply();

		result.setCustomer(principal);
		result.setOffer(offer);
		result.setStatus("PENDING");

		return result;
	}

	public Apply save(final Apply apply) {
		Assert.notNull(apply);
		Apply result;

		final Customer principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
		Assert.isTrue(principal.equals(apply.getCustomer()));
		Assert.isTrue(this.actorService.checkCreditCard(principal));

		for (final Apply a : principal.getApplies())
			Assert.isTrue(!(a.getOffer().equals(apply.getOffer())));

		result = this.applyRepository.save(apply);

		return result;
	}

	public Apply saveEdit(final Apply apply) {
		Assert.notNull(apply);
		Apply result;

		final Customer principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
		Assert.isTrue(principal.equals(apply.getCustomer()));
		Assert.isTrue(apply.getStatus().equals("PENDING"));

		result = this.applyRepository.save(apply);

		return result;
	}

	public Apply saveStatus(final Apply apply) {
		Assert.notNull(apply);
		Apply result;

		final Caretaker principal = (Caretaker) this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Caretaker.class, principal);
		Assert.isTrue(principal.equals(apply.getOffer().getCaretaker()));

		result = this.applyRepository.save(apply);

		return result;
	}

	public void delete(final Apply apply) {
		Assert.notNull(apply);
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
		Assert.isTrue(principal.equals(apply.getCustomer()));
		Assert.isTrue(apply.getStatus().equals("PENDING"));

		this.applyRepository.delete(apply);
	}

	//Other Business Methods =========================================================================

	public Collection<Apply> findAllByCustomer(final Customer customer) {
		Assert.notNull(customer);
		Collection<Apply> result;

		result = this.applyRepository.findAllByCustomer(customer.getId());

		return result;
	}

	public Collection<Apply> findAllByCaretaker(final Actor actor) {
		Assert.notNull(actor);
		Collection<Apply> result;

		result = this.applyRepository.findAllByCaretakerId(actor.getId());

		return result;
	}

}
