
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InscriptionRepository;
import domain.Customer;
import domain.Inscription;
import domain.Kindergarten;
import domain.Lesson;
import domain.Shift;
import forms.InscriptionForm;

@Service
@Transactional
public class InscriptionService {

	//Managed Repository =============================================================================

	@Autowired
	private InscriptionRepository	inscriptionRepository;

	@Autowired
	private KindergartenService		kindergartenService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ShiftService			shiftService;


	//	@Autowired
	//	private CustomerRepository		customerRepository;
	//
	//	@Autowired
	//	private InvoiceService			invoiceService;

	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Inscription findOne(final int inscriptionId) {
		Assert.notNull(inscriptionId);

		Inscription result;
		result = this.inscriptionRepository.findOne(inscriptionId);

		return result;
	}

	public Inscription create(final Lesson lesson) {
		Inscription result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
		result = new Inscription();

		result.setCustomer(principal);
		result.setLesson(lesson);

		return result;
	}

	public InscriptionForm createForm(Lesson lesson, Shift shift) {
		InscriptionForm result;
		Customer principal;

		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
		result = new InscriptionForm();

		result.setCustomer(principal);
		result.setLesson(lesson);
		result.setShiftId(shift.getId());

		return result;
	}

	public Inscription save(final Inscription inscription) {
		Assert.notNull(inscription);
		Inscription result;

		final Customer principal = this.customerService.findByPrincipal();
		Assert.isTrue(principal.equals(inscription.getCustomer()));
		Assert.isTrue(this.actorService.checkCreditCard(principal));

		result = this.inscriptionRepository.save(inscription);

		return result;
	}

	public Inscription save2(final InscriptionForm inscriptionForm) {
		Assert.notNull(inscriptionForm);
		Inscription result;
		Inscription inscription = new Inscription();

		final Customer principal = this.customerService.findByPrincipal();
		Assert.isTrue(principal.equals(inscriptionForm.getCustomer()));
		Assert.isTrue(this.actorService.checkCreditCard(principal));

		Shift shift = shiftService.findOne(inscriptionForm.getShiftId());
		shift.setAttendance(shift.getAttendance() - 1);
		shiftService.save(shift);

		inscription.setCustomer(inscriptionForm.getCustomer());
		inscription.setId(inscriptionForm.getId());
		inscription.setLesson(inscriptionForm.getLesson());
		inscription.setVersion(inscriptionForm.getVersion());
		inscription.setInvoice(inscriptionForm.getInvoice());

		result = this.inscriptionRepository.save(inscription);

		return result;
	}

	public void delete(final Inscription inscription) {
		Assert.notNull(inscription);
		Kindergarten principal;

		principal = this.kindergartenService.findByPrincipal();
		Assert.notNull(principal);

		this.inscriptionRepository.delete(inscription);
	}
	//Other Business Methods =========================================================================

	public Integer countIncriptionByLessorForCustomer(final int idLessor, final int idCustomer) {
		Integer result;

		result = this.inscriptionRepository.countIncriptionByLessorForCustomer(idLessor, idCustomer);

		return result;
	}

	public Collection<Inscription> findAllByCustomer(final Customer customer) {
		Assert.notNull(customer);
		Collection<Inscription> result;

		result = this.inscriptionRepository.findAllByCustomer(customer.getId());

		return result;
	}

}
