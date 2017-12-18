
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AssessmentRepository;
import domain.Assessment;
import domain.Comentable;
import domain.Customer;
import forms.AssessmentForm;

@Transactional
@Service
public class AssessmentService {

	// Repositories
	// ====================================================================

	@Autowired
	private AssessmentRepository	assessmentRepository;

	// Supporting services
	// ====================================================================

	@Autowired
	private ComentableService		comentableService;

	@Autowired
	private CustomerService			customerService;


	// Simple CRUD methods
	// ====================================================================

	public Assessment findOne(final int assessmentId) {
		Assert.isTrue(assessmentId != 0);
		Assessment result;

		result = this.assessmentRepository.findOne(assessmentId);

		return result;
	}

	public Collection<Assessment> findAll() {
		Collection<Assessment> result;

		result = this.assessmentRepository.findAll();

		return result;
	}

	public Assessment create(final Comentable comentable) {
		Assessment result;
		Customer principal;
		Date createMoment;

		principal = this.customerService.findByPrincipal();
		Assert.notNull(principal);

		final long l = 10;
		createMoment = new Date(System.currentTimeMillis() - l);
		result = new Assessment();
		result.setCreateMoment(createMoment);
		result.setCustomer(principal);
		comentable.getAssessments().add(result);
		result.setComentable(comentable);

		return result;
	}

	public Assessment save(final Assessment assessment) {
		Assert.notNull(assessment);
		Assessment result;

		result = this.assessmentRepository.save(assessment);

		return result;
	}


	// Other business methods
	// ===================================================================

	@Autowired
	private Validator	validator;


	public Assessment reconstruct(final AssessmentForm assessmentForm, final BindingResult binding) {
		//Comentable comentable2 = comentableService.findOne(assessmentForm.getIdComentable());
		final Comentable comentable = this.comentableService.findOneAux(assessmentForm.getIdComentable());
		final Assessment res = this.create(comentable);
		res.setRate(assessmentForm.getRate());
		res.setText(assessmentForm.getText());
		res.setTitle(assessmentForm.getTitle());
		this.validator.validate(res, binding);

		return res;

	}

	public Collection<Assessment> filterAssessments(final int actorId) {
		Collection<Assessment> result;

		result = this.assessmentRepository.findAllByActor(actorId);

		return result;
	}

	public Collection<Assessment> showWelcome() {
		return this.assessmentRepository.findWelcome();
	}

	public Collection<Assessment> find4Assessments() {
		Collection<Assessment> result;
		final Collection<Assessment> res = new ArrayList<Assessment>();
		result = this.assessmentRepository.find4Assessments();

		int i = 0;
		for (final Assessment a : result) {
			res.add(a);
			i++;
			if (i == 4)
				break;
		}
		Assert.notNull(res);
		return res;
	}

}
