
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Inscription;
import domain.Lesson;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class InscriptionServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private InscriptionService	inscriptionService;

	@Autowired
	private LessonService		lessonService;


	// Tests
	// ====================================================

	/*
	 * Inscribe his or her baby to a lesson as long as the customer has a valid credit card, the shift attendance
	 * is available and the lesson has not started. Is not possible being inscribed in more than one shift from the
	 * same lesson.
	 * 
	 * En este test, se comprueba la inscripción del bebe de cierto customer en una leccion registrada en el sistema. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Create a new inscription.
	 * 
	 * En este caso de uso simularemos la creación de una inscripción.
	 */

	public void inscriptionCreateTest(final String username, final int idLesson, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Lesson lesson;
			lesson = this.lessonService.findOne(idLesson);
			final Inscription result = this.inscriptionService.create(lesson);

			this.inscriptionService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverInscriptionCreateTest() {

		final Object testingData[][] = {
			// Crear una inscripción estando logueado como kindergarten -> true
			{
				"customer1", 18, null
			},
			// Crear una inscripción sin estar logueado --> false
			{
				null, 18, IllegalArgumentException.class
			},
			// Crear una inscripción logueado como customer -> true
			{
				"customer1", 20, null
			},
			// Crear una inscripción logueado como kindergarten -> false
			{
				"kindergarten1", 20, IllegalArgumentException.class
			},
			// Crear una inscripción logueado como admin -> false
			{
				"admin", 20, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.inscriptionCreateTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}

}
