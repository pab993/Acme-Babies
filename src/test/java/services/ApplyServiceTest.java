
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Apply;
import domain.Offer;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplyServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ApplyService	applyService;

	@Autowired
	private OfferService	offerService;


	// Tests
	// ====================================================

	/*
	 * Create an apply
	 * 
	 * En este test, se comprueba la creación de una nueva aplicacion, así como su edicción y su eliminación. Estas acciones pueden realizarla el actor 'customer'. Luego, si cualquier otro
	 * actor realiza dicha acción, el sistema impedirá la creación, edicion o borrado del mismo. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Create a new apply.
	 * 
	 * En este caso de uso simularemos la creación de una aplicacion.
	 */

	public void applyCreateTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Offer offer = this.offerService.findOne(17);
			final Apply result = this.applyService.create(offer);

			result.setCounterOffer(120.0);
			result.setDays(2);
			result.setSurname("surnameTest");

			this.applyService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}
	/*
	 * Edit an apply.
	 * 
	 * En este caso de uso un customer puede editar una aplicacion.
	 */

	public void editApplyTest(final String username, final String name, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Apply result;

			result = this.applyService.findOne(21);

			result.setName(name);

			this.applyService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete an apply.
	 * 
	 * En este caso de uso un customer puede borrar una aplicacion existente.
	 */

	public void deleteApplyTest(final String username, final int applyId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Apply result;

			result = this.applyService.findOne(applyId);

			this.applyService.delete(result);

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
			// Crear una aplicacion estando logueado como customer -> true
			{
				"customer1", "name1", null
			},
			// Crear una aplicacion sin estar logueado --> false
			{
				null, "name1", IllegalArgumentException.class
			},
			// Crear una leccion logueado como kindergarten -> false
			{
				"kindergarten1", "name1", IllegalArgumentException.class
			},
			// Crear una leccion logueado como admin -> false
			{
				"admin", "name1", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.applyCreateTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	@Test
	public void driverEditLessonTest() {

		final Object testingData[][] = {
			// Editar una leccion sin estar logueado -> false
			{
				null, "name2", IllegalArgumentException.class
			},
			// Editar una leccion logueado como kindergarten -> false
			{
				"kindergarten", "name3", IllegalArgumentException.class
			},
			// Editar una leccion logueado como customer -> true
			{
				"customer1", "title3", IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editApplyTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteLessonTest() {

		final Object testingData[][] = {
			// Borrar una aplicacion sin estar logueado -> false
			{
				null, 21, IllegalArgumentException.class
			},
			// Borrar una aplicacion aceptada-> false
			{
				"customer1", 23, IllegalArgumentException.class
			},
			// Borrar una leccion estando logueado como customer -> true
			{
				"customer1", 21, null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteApplyTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
