
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Access(AccessType.PROPERTY)
@Entity
public class Invoice extends DomainEntity {

	// Attributes
	// ====================================================================================

	private Date	createMoment;
	private String	label;
	private String	concept;
	private Double	totalPrice;


	// Constructors
	// ====================================================================================

	public Invoice() {
		super();
	}

	// Getters & Setters
	// ====================================================================================

	@Past
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateMoment() {
		return this.createMoment;
	}

	public void setCreateMoment(final Date createMoment) {
		this.createMoment = createMoment;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Column(unique = true)
	@Pattern(regexp = "^_[a-zA-Z]{2}[a-zA-Z0-9]{5}$")
	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getConcept() {
		return this.concept;
	}

	public void setConcept(final String concept) {
		this.concept = concept;
	}

	@NotNull
	@Range(min = 1, max = 1000)
	@Digits(fraction = 2, integer = 12)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(final Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	// Relationships
	// =================================================================

	private Inscription	inscription;


	@Valid
	@OneToOne(optional = false)
	public Inscription getInscription() {
		return this.inscription;
	}

	public void setInscription(final Inscription inscription) {
		this.inscription = inscription;
	}

}
