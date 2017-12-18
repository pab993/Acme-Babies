
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Assessment;
import domain.Comentable;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AssessmentServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private AssessmentService	assessmentService;

	@Autowired
	private ComentableService	comentableService;


	// Tests
	// ====================================================

	/*
	 * Place an assessment about a caretaker or a lesson.
	 * 
	 * En este caso de uso se crean y se guardan los assessments que queramos realizar sobre un objeto comentable siempre y cuando
	 * nos encontramos autentificados, por lo tanto es accesible para cualquier 'customer'.
	 * Para provocar el error, se intenta guardar mediante un usuario no autentificado e incluyendo un objeto comentable no válido.
	 */

	protected void createTest(final String username, final int idComentable, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			final Comentable comentable = this.comentableService.findOneAux(idComentable);
			this.assessmentService.create(comentable);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void saveTest(final String username, final int idComentable, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			final Comentable comentable = this.comentableService.findOneAux(idComentable);
			final Assessment assessment = this.assessmentService.create(comentable);
			assessment.setText("test");
			assessment.setTitle("test");
			assessment.setRate(2);

			this.assessmentService.save(assessment);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ====================================================

	@Test
	public void driverCreateAssessment() {

		final Object testingData1[][] = {
			// Creación de un assessment si estoy autentificado como customer -> true
			{
				"customer1", 18, null
			},
			// Creación de un assessment si estoy autentificado como admin -> false
			{
				"admin", 18, IllegalArgumentException.class
			},
			// Creación de un assessment si no estoy autentificado -> false
			{
				null, 19, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.createTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

	@Test
	public void driverSaveAssessment() {

		final Object testingData[][] = {
			// Si existe el idComentable -> true
			{
				"customer1", 19, null
			},
			// Si no estamos autentificados para guardar un assessment -> false
			{
				null, 19, IllegalArgumentException.class
			}, {
				// Si no estamos autentificados para guardar un assessment y el idComentable no existe -> false
				null, 989, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.saveTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
