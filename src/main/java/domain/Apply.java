
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Apply extends DomainEntity {

	//Attributes 
	// =================================================================

	private String				name;
	private String				surname;
	private Collection<String>	observations;
	private Integer				days;
	private Double				counterOffer;
	private String				status;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@ElementCollection
	public Collection<String> getObservations() {
		return this.observations;
	}

	public void setObservations(final Collection<String> observations) {
		this.observations = observations;
	}

	@NotNull
	@Min(1)
	public Integer getDays() {
		return this.days;
	}
	public void setDays(final Integer days) {
		this.days = days;
	}

	@NotNull
	@Range(min = 1, max = 1000)
	public Double getCounterOffer() {
		return this.counterOffer;
	}
	public void setCounterOffer(final Double counterOffer) {
		this.counterOffer = counterOffer;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships
	// =================================================================

	private Customer	customer;
	private Offer		offer;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(final Offer offer) {
		this.offer = offer;
	}

}
