
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Lesson;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class LessonServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private LessonService	lessonService;


	// Tests
	// ====================================================

	/*
	 * Create a lesson, edit and delete them, a lesson can not be edited or deleted if you enroll a baby. the
	 * The system must notify kindergarten of the cost of creating the lesson each time a new one is created.
	 * 
	 * En este test, se comprueba la creaci�n de una nueva lecci�n, as� como su edicci�n y su eliminaci�n. Estas acciones pueden realizarla el actor 'kindergarten'. Luego, si cualquier otro
	 * actor realiza dicha acci�n, el sistema impedir� la creaci�n, edicion o borrado del mismo. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicaci�n.
	 */

	/*
	 * Create a new lesson.
	 * 
	 * En este caso de uso simularemos la creaci�n de una leccion.
	 */

	@SuppressWarnings("deprecation")
	public void lessonCreateTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Lesson result = this.lessonService.create();

			final Date startDate = new Date();
			startDate.setYear(2017);
			final Date finishDate = new Date();
			finishDate.setYear(2018);

			result.setTitle(title);
			result.setDescription("description");
			result.setStartDate(startDate);
			result.setFinishDate(finishDate);
			result.setPrice(180.0);

			this.lessonService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit a lesson.
	 * 
	 * En este caso de uso un kindergarten puede editar una leccion.
	 */

	public void editLessonTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Lesson result;

			result = this.lessonService.findOne(18);

			result.setTitle(name);

			this.lessonService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a lesson.
	 * 
	 * En este caso de uso un kindergarten puede borrar una leccion existente.
	 */

	public void deleteLessonTest(final String username, final int lessonId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Lesson result;

			result = this.lessonService.findOne(lessonId);

			this.lessonService.delete(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverLessonCreateTest() {

		final Object testingData[][] = {
			// Crear una leccion estando logueado como kindergarten -> true
			{
				"kindergarten1", "title1", null
			},
			// Crear una leccion sin estar logueado --> false
			{
				null, "title1", IllegalArgumentException.class
			},
			// Crear una leccion logueado como customer -> false
			{
				"customer1", "title1", IllegalArgumentException.class
			},
			// Crear una leccion logueado como admin -> false
			{
				"admin", "title1", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.lessonCreateTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	@Test
	public void driverEditLessonTest() {

		final Object testingData[][] = {
			// Editar una leccion sin estar logueado -> false
			{
				null, "title2", IllegalArgumentException.class
			},
			// Editar una leccion logueado como dancer -> false
			{
				"customer1", "title3", IllegalArgumentException.class
			},
			// Editar una leccion logueado como academy -> false
			{
				"admin", "title3", IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editLessonTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteLessonTest() {

		final Object testingData[][] = {
			// Borrar una leccion estando logueado como kindergarten -> true
			{
				"kindergarten1", 18, null
			},
			// Borrar una leccion sin estar logueado -> false
			{
				null, 19, IllegalArgumentException.class
			},
			// Borrar una leccion que se imparte en un curso -> false
			{
				"admin", 9, IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteLessonTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
