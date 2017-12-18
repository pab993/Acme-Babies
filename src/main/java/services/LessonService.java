
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LessonRepository;
import domain.Actor;
import domain.Inscription;
import domain.Kindergarten;
import domain.Lesson;

@Service
@Transactional
public class LessonService {

	//Managed Repository =============================================================================

	@Autowired
	private LessonRepository	lessonRepository;

	@Autowired
	private KindergartenService	kindergartenService;

	@Autowired
	private InscriptionService	inscriptionService;

	@Autowired
	private ActorService		actorService;


	//Constructor methods ============================================================================

	//Simple CRUD methods ============================================================================

	public Lesson findOne(final int lessonId) {
		Assert.notNull(lessonId);

		Lesson result;
		result = this.lessonRepository.findOne(lessonId);

		return result;
	}

	public Collection<Lesson> findAll() {
		Collection<Lesson> result;

		result = this.lessonRepository.findAll();

		return result;
	}

	public Lesson create() {
		Lesson result;
		Kindergarten principal;
		Collection<Inscription> inscriptions;

		principal = this.kindergartenService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Kindergarten.class, principal);
		inscriptions = new HashSet<Inscription>();

		result = new Lesson();
		result.setKindergarten(principal);
		result.setInscriptions(inscriptions);

		return result;
	}

	public Lesson save(final Lesson lesson) {
		Assert.notNull(lesson);
		Assert.isTrue(lesson.getFinishDate().after(lesson.getStartDate()));
		Lesson result;
		Collection<Inscription> inscriptions;

		Kindergarten principal = this.kindergartenService.findByPrincipal();
		Assert.isTrue(principal.equals(lesson.getKindergarten()));
		Assert.isTrue(this.actorService.checkCreditCard(principal));

		inscriptions = lesson.getInscriptions();
		Assert.isTrue(inscriptions != null && inscriptions.isEmpty());

		result = this.lessonRepository.save(lesson);

		return result;
	}

	public void delete(final Lesson lesson) {
		Assert.notNull(lesson);
		Kindergarten principal;
		Collection<Inscription> inscriptions;

		principal = this.kindergartenService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.equals(lesson.getKindergarten()));
		Assert.isInstanceOf(Kindergarten.class, principal);

		inscriptions = lesson.getInscriptions();

		if (inscriptions != null && inscriptions.isEmpty())
			for (final Inscription inscription : inscriptions)
				this.inscriptionService.delete(inscription);

		this.lessonRepository.delete(lesson);
	}

	public Collection<Lesson> getLessonByKeyWord(final String keyWord) {
		Assert.notNull(keyWord);
		Collection<Lesson> result;

		result = this.lessonRepository.searchLessonByWords(keyWord);

		return result;
	}

	public Collection<Lesson> findAllByCustomer(final Actor actor) {
		Assert.notNull(actor);
		Collection<Lesson> result;

		result = this.lessonRepository.findAllByCustomerId(actor.getId());

		return result;
	}

	public Collection<Lesson> findAllByKindergarten(final Actor actor) {
		Assert.notNull(actor);
		Collection<Lesson> result;

		result = this.lessonRepository.findAllByKindergartenId(actor.getId());

		return result;
	}

	
	public String getMinAvgMaxLessonsPerKindergarten() {
		String result;

		result = "Min: " + this.lessonRepository.minLessonsPerKindergarten() + " Avg: " + this.lessonRepository.avgLessonsPerKindergarten() + " Max" + this.lessonRepository.maxLessonsPerKindergarten();

		return result;
	}
	
public Collection<Lesson> getLessonWithMoreAssessments(){
		
		Collection<Lesson> res;
		
		res = lessonRepository.lessonWithMoreAssessments();
	
	return res;
	}
	
}
