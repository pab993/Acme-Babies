
package services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import repositories.CreditCardRepository;
import domain.Actor;
import domain.BrandNameCreditCard;
import domain.CreditCard;

@Transactional
@Service
public class CreditCardService {

	//Repository
	//======================================================================

	@Autowired
	private CreditCardRepository	creditCardRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService			actorService;


	//CRUD methods
	//=======================================================================

	public CreditCard findOne(int id) {
		CreditCard creditCard;

		creditCard = creditCardRepository.findOne(id);
		Assert.notNull(creditCard);

		return creditCard;
	}

	public CreditCard create() {

		CreditCard cc = new CreditCard();
		return cc;

	}

	public CreditCard save(CreditCard c) {
		Assert.notNull(c);
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(c.getActor().getId() == actor.getId());
		//Assert.isTrue(checkValidity(c));
		//		delete(c);
		CreditCard creditCardRes = creditCardRepository.save(c);
		return creditCardRes;
	}

	public void delete(CreditCard c) {
		Assert.notNull(c);
		Actor actor = actorService.findByPrincipal();
		Assert.isTrue(c.getActor().getId() == actor.getId());
		Assert.isTrue(c.getId() != 0);
		Assert.isTrue(creditCardRepository.exists(c.getId()));
		creditCardRepository.delete(c);
	}

	//Other bussiness methods
	//=======================================================================

	public int[] stringToArray(String number) {
		char[] a = number.toCharArray();
		int[] n = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			int x = Character.getNumericValue(a[i]);
			n[i] = x;
		}
		return n;
	}
	public boolean verificacionLuhn(int[] digits, String number, BindingResult binding) {
		FieldError error;
		String[] codigos;
		int sum = 0;
		int length = digits.length;
		for (int i = 0; i < length; i++) {
			// sacar los digitos en orden inverso
			int digit = digits[length - i - 1];

			// cada segundo n�mero se multiplica por 2
			if (i % 2 == 1) {
				digit = digit * 2;
			}
			if (digit > 9) {
				digit = digit - 9;
			}
			sum = sum + digit;
		}

		if (sum % 10 != 0) {
			codigos = new String[1];
			codigos[0] = "cc.luhn.error";
			error = new FieldError("creditCard", "number", number, false, codigos, null, "");
			binding.addError(error);
		}
		return sum % 10 == 0;
	}

	public boolean verificacionLuhn(int[] digits) {
		int sum = 0;
		int length = digits.length;
		for (int i = 0; i < length; i++) {
			// sacar los digitos en orden inverso
			int digit = digits[length - i - 1];

			// cada segundo n�mero se multiplica por 2
			if (i % 2 == 1) {
				digit = digit * 2;
			}
			if (digit > 9) {
				digit = digit - 9;
			}
			sum = sum + digit;
		}

		return sum % 10 == 0;
	}

	public CreditCard findByCustomerId(int customerId) {
		CreditCard creditCard;
		Assert.notNull(customerId);

		creditCard = creditCardRepository.findByCustomerId(customerId);

		return creditCard;

	}

	public CreditCard findByCaretakerId(int caretakerId) {
		CreditCard creditCard;
		Assert.notNull(caretakerId);

		creditCard = creditCardRepository.findByCaretakerId(caretakerId);

		return creditCard;

	}

	public CreditCard findByKindergartenId(int kindergartenId) {
		CreditCard creditCard;
		Assert.notNull(kindergartenId);

		creditCard = creditCardRepository.findByKindergartenId(kindergartenId);

		return creditCard;

	}

	public boolean checkValidity(CreditCard creditCard, BindingResult binding) {

		boolean res = false;
		int[] n = stringToArray(creditCard.getNumber());

		if (checkBrandName(creditCard, binding) && verificacionLuhn(n, creditCard.getNumber(), binding) && checkExpirationDate(creditCard, binding)) {
			res = true;
		}
		return res;
	}

	public boolean checkBrandName(CreditCard creditCard, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean res = false;

		for (BrandNameCreditCard bn : BrandNameCreditCard.values()) {
			if (bn.name().equals(creditCard.getBrandName())) {
				res = true;
			}
		}

		if (res == false) {
			codigos = new String[1];
			codigos[0] = "cc.brandname.error";
			error = new FieldError("creditCard", "brandName", creditCard.getBrandName(), false, codigos, null, "");
			binding.addError(error);
		}

		return res;
	}

	public boolean checkBrandName(CreditCard creditCard) {
		boolean res = false;

		for (BrandNameCreditCard bn : BrandNameCreditCard.values()) {
			if (bn.name().equals(creditCard.getBrandName())) {
				res = true;
			}
		}
		return res;
	}

	public boolean checkExpirationDate(CreditCard creditCard, BindingResult binding) {
		FieldError error;
		String[] codigos;
		boolean res = true;

		long l = 10;
		Calendar actualCalendar = Calendar.getInstance();
		Date actual = new Date(System.currentTimeMillis() - l);
		actualCalendar.setTime(actual);

		System.out.print(creditCard.getExpirationYear());
		System.out.print("//");
		System.out.print(creditCard.getExpirationMonth());
		System.out.print("-------");
		System.out.print(actualCalendar.get(Calendar.YEAR));
		System.out.print("//");
		System.out.print(actualCalendar.get(Calendar.MONTH) + 1);
		System.out.print("-------");

		if (creditCard.getExpirationYear() < actualCalendar.get(Calendar.YEAR)) {
			res = false;
			codigos = new String[1];
			codigos[0] = "cc.expirationYear.error";
			error = new FieldError("creditCard", "expirationYear", creditCard.getExpirationYear(), false, codigos, null, "");
			binding.addError(error);

		} else if (creditCard.getExpirationYear() == actualCalendar.get(Calendar.YEAR) && (creditCard.getExpirationMonth() < actualCalendar.get(Calendar.MONTH) + 1)) {
			res = false;
			codigos = new String[1];
			codigos[0] = "cc.expirationMonth.error";
			error = new FieldError("creditCard", "expirationMonth", creditCard.getExpirationMonth(), false, codigos, null, "");
			binding.addError(error);
		} //else if (creditCard.getExpirationYear() > actualCalendar.get(Calendar.YEAR) && (creditCard.getExpirationMonth() < actualCalendar.get(Calendar.MONTH) + 1)) {
		//			res = false;
		//			codigos = new String[1];
		//			codigos[0] = "cc.expirationMonth.error";
		//			error = new FieldError("creditCard", "expirationMonth", creditCard.getExpirationMonth(), false, codigos, null, "");
		//			binding.addError(error);
		//		}

		return res;
	}

	public boolean checkExpirationDate(CreditCard creditCard) {
		boolean res = true;

		long l = 10;
		Calendar actualCalendar = Calendar.getInstance();
		Date actual = new Date(System.currentTimeMillis() - l);
		actualCalendar.setTime(actual);

		if (creditCard.getExpirationYear() < actualCalendar.get(Calendar.YEAR)) {
			res = false;

		} else if (creditCard.getExpirationYear() >= actualCalendar.get(Calendar.YEAR) && creditCard.getExpirationMonth() < actualCalendar.get(Calendar.MONTH)) {
			res = false;
		}

		return res;
	}

	public CreditCard reconstruct(CreditCard creditCard, BindingResult binding) {

		CreditCard result = new CreditCard();

		result.setActor(creditCard.getActor());
		result.setBrandName(creditCard.getBrandName());
		result.setCVV(creditCard.getCVV());
		result.setExpirationMonth(creditCard.getExpirationMonth());
		result.setExpirationYear(creditCard.getExpirationYear());
		result.setHolderName(creditCard.getHolderName());
		result.setNumber(creditCard.getNumber());
		result.setId(creditCard.getId());
		result.setVersion(creditCard.getVersion());

		checkValidity(result, binding);

		return result;
	}

}
