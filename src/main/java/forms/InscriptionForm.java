
package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Customer;
import domain.Invoice;
import domain.Lesson;

public class InscriptionForm {

	public InscriptionForm() {
		super();
	}


	private int			id;
	private int			version;
	private Customer	customer;
	private Lesson		lesson;
	private Invoice		invoice;
	private int			shiftId;


	@NotNull
	@Valid
	public Customer getCustomer() {
		return this.customer;
	}
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@NotNull
	@Valid
	public Lesson getLesson() {
		return this.lesson;
	}
	public void setLesson(final Lesson lesson) {
		this.lesson = lesson;
	}

	@Valid
	public Invoice getInvoice() {
		return this.invoice;
	}
	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
