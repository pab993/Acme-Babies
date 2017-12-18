
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "lesson_id")
})
public class Inscription extends DomainEntity {

	//Relationships
	// =======================================================

	private Customer	customer;
	private Lesson		lesson;
	private Invoice		invoice;


	@NotNull
	@Valid
	@ManyToOne
	public Customer getCustomer() {
		return this.customer;
	}
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@NotNull
	@Valid
	@ManyToOne
	public Lesson getLesson() {
		return this.lesson;
	}
	public void setLesson(final Lesson lesson) {
		this.lesson = lesson;
	}

	@Valid
	@OneToOne(optional = true, mappedBy = "inscription")
	public Invoice getInvoice() {
		return this.invoice;
	}
	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

}
