
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "customer_id"), @Index(columnList = "rate")
})
public class Assessment extends DomainEntity {

	// Constructors
	// ====================================================================================

	// Attributes
	// ====================================================================================

	private String	title;
	private Date	createMoment;
	private String	text;
	private Integer	rate;


	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateMoment() {
		return this.createMoment;
	}

	public void setCreateMoment(final Date createMoment) {
		this.createMoment = createMoment;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@NotNull
	@Range(min = 0, max = 5)
	public Integer getRate() {
		return this.rate;
	}

	public void setRate(final Integer rate) {
		this.rate = rate;
	}


	// Relationships
	// ====================================================================================

	private Customer	customer;
	private Comentable	comentable;


	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Valid
	@ManyToOne(optional = false)
	public Comentable getComentable() {
		return this.comentable;
	}

	public void setComentable(final Comentable comentable) {
		this.comentable = comentable;
	}

}
