
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Relationships
	// =======================================================

	private Collection<Assessment>	writeAssessments;
	private Collection<Apply>		applies;
	private Collection<Inscription>	inscriptions;


	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Assessment> getWriteAssessments() {
		return this.writeAssessments;
	}

	public void setWriteAssessments(final Collection<Assessment> writeAssessments) {
		this.writeAssessments = writeAssessments;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Apply> getApplies() {
		return this.applies;
	}

	public void setApplies(final Collection<Apply> applies) {
		this.applies = applies;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(final Collection<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
}
