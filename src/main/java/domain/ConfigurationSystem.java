
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class ConfigurationSystem extends DomainEntity {

	// Attributes
	// ====================================================================================

	private String	banner;
	private Double	lessonTax;


	// Constructors
	// ====================================================================================

	public ConfigurationSystem() {
		super();
	}

	// Getters & setters
	// ====================================================================================

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotNull
	@Range(min = 0, max = 1000)
	public Double getLessonTax() {
		return this.lessonTax;
	}

	public void setLessonTax(final Double lessonTax) {
		this.lessonTax = lessonTax;
	}

}
