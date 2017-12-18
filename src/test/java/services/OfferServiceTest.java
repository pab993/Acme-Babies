
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Offer;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private OfferService	offerService;


	// Tests
	// ====================================================

	/*
	 * Create a offer, edit and delete them, a offer can not be edited or deleted if you enroll a baby. the
	 * The system must notify kindergarten of the cost of creating the offer each time a new one is created.
	 * 
	 * En este test, se comprueba la creación de una nueva oferta, así como su edicción y su eliminación. Estas acciones pueden realizarla el actor 'kindergarten'. Luego, si cualquier otro
	 * actor realiza dicha acción, el sistema impedirá la creación, edicion o borrado del mismo. Para ello
	 * introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Create a new offer.
	 * 
	 * En este caso de uso simularemos la creación de una oferta.
	 */

	public void offerCreateTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			final Offer result = this.offerService.create();

			result.setTitle(title);
			result.setDescription("descriptiontest");
			result.setPrice(180.0);

			this.offerService.save(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Edit an offer.
	 * 
	 * En este caso de uso un caretaker puede editar una oferta.
	 */

	public void editOfferTest(final String username, final String title, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Offer result;

			result = this.offerService.findOne(15);

			result.setTitle(title);

			this.offerService.save(result);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * Delete a offer.
	 * 
	 * En este caso de uso un caretaker puede borrar una oferta existente.
	 */

	public void deleteOfferTest(final String username, final int offerId, final Class<?> expected) {
		Class<?> caught = null;

		try {

			this.authenticate(username);

			Offer result;

			result = this.offerService.findOne(offerId);

			this.offerService.delete(result);

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ===================================================

	@Test
	public void driverOfferCreateTest() {

		final Object testingData[][] = {
			// Crear una oferta estando logueado como caretaker -> true
			{
				"caretaker1", "title1", null
			},
			// Crear una oferta sin estar logueado --> false
			{
				null, "title1", IllegalArgumentException.class
			},
			// Crear una oferta logueado como customer -> false
			{
				"customer1", "title1", IllegalArgumentException.class
			},
			// Crear una oferta logueado como admin -> false
			{
				"admin", "title1", IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.offerCreateTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	@Test
	public void driverEditOfferTest() {

		final Object testingData[][] = {
			// Editar una oferta sin estar logueado -> false
			{
				null, "title2", IllegalArgumentException.class
			},
			// Editar una oferta logueado como caretaker -> true
			{
				"caretaker1", "title3", null
			},
			// Editar una oferta logueado como admin-> false
			{
				"admin", "title3", IllegalArgumentException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.editOfferTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void driverDeleteOfferTest() {

		final Object testingData[][] = {
			// Borrar una oferta sin estar logueado -> false
			{
				null, 15, IllegalArgumentException.class
			},
			// Borrar una oferta que se imparte en un curso -> false
			{
				"admin", 15, IllegalArgumentException.class
			},
			// Borrar una oferta estando logueado como caretaker -> true
			{
				"caretaker2", 17, null
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteOfferTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
