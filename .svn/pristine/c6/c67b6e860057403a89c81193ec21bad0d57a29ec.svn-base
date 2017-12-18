
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Caretaker;
import domain.CreditCard;
import domain.Kindergarten;
import forms.ActorForm;

@Service
@Transactional
public class ActorService {

	//Managed Repository =============================================================================

	@Autowired
	private ActorRepository		actorRepository;

	//Services
	// ===============================================================================================

	@Autowired
	private CreditCardService	creditCardService;


	//SCRUDs Methods
	//===============================================================================================

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Assert.notNull(actor.getUserAccount());
		Actor result;

		final Actor principal = this.findByPrincipal();
		Assert.notNull(principal);

		result = this.actorRepository.save(actor);

		return result;
	}

	public Actor findOne(final int actorId) {
		Actor result;

		result = this.actorRepository.findOne(actorId);

		return result;
	}

	//Other Business Methods =========================================================================

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result;

		result = this.actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	public ActorForm reconstructToForm(final Actor actor) {
		final ActorForm actorForm = new ActorForm();

		actorForm.setName(actor.getName());
		actorForm.setSurname(actor.getSurname());
		actorForm.setPhoneNumber(actor.getPhoneNumber());
		actorForm.setEmail(actor.getEmail());
		actorForm.setAddress(actor.getAddress());
		actorForm.setPicture(actor.getPicture());

		return actorForm;

	}

	public Actor reconstruct1(final ActorForm actorForm, final BindingResult binding) {
		Actor result;

		result = this.findByPrincipal();

		this.comprobarPhoneNumber(actorForm.getPhoneNumber(), binding);

		return result;
	}

	//Hay dos reconstructs porque por alguna razón aquí se guardan los cambios en la base de datos en este metodo. Así que de esta manera hago un "rollback".
	public Actor reconstruct2(final ActorForm actorForm, final BindingResult binding) {
		Actor result;

		result = this.findByPrincipal();
		result.setName(actorForm.getName());
		result.setSurname(actorForm.getSurname());
		result.setPhoneNumber(actorForm.getPhoneNumber());
		result.setEmail(actorForm.getEmail());
		result.setAddress(actorForm.getAddress());
		result.setPicture(actorForm.getPicture());

		this.comprobarPhoneNumber(actorForm.getPhoneNumber(), binding);

		return result;
	}

	private boolean comprobarPhoneNumber(final String phoneNumber, final BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean result;

		if (phoneNumber == null || phoneNumber.isEmpty())
			result = true;
		else
			result = false;

		if (!result)
			if (phoneNumber.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"))
				result = true;
			else {
				codigos = new String[1];
				codigos[0] = "actor.phoneNumber.error";
				error = new FieldError("actorForm", "phoneNumber", phoneNumber, false, codigos, null, "");
				binding.addError(error);
			}

		return result;
	}

	public boolean checkCreditCard(final Actor actor) {
		boolean result = false;
		CreditCard creditCard = null;

		if (Caretaker.class.isInstance(actor))
			creditCard = this.creditCardService.findByCaretakerId(actor.getId());
		else if (Kindergarten.class.isInstance(actor))
			creditCard = this.creditCardService.findByKindergartenId(actor.getId());
		else
			creditCard = this.creditCardService.findByCustomerId(actor.getId());

		if (!(creditCard.equals(null)))
			result = true;

		return result;
	}

}
