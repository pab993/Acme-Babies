
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Kindergarten;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class KindergartenServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private KindergartenService	kindergartenService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a new kindergarten in our system, the system must check the username,
	 * the passwords, the name, the surname, the phone, the email, the address, and the picture.
	 * 
	 * En este test, se comprueba el registro de una nueva guardería.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	/*
	 * Register a new kinndergarten.
	 * 
	 * En este caso de uso simularemos el registro de una guardería.
	 */

	public void kindergartenRegisterTest(String username, String password, String passwordRepeat, String name, String surname, String phoneNumber, String email, String address, String picture, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Kindergarten result = kindergartenService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			Assert.notNull(passwordRepeat);
			Assert.isTrue(password.equals(passwordRepeat));
			if (phoneNumber == null) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(phoneNumber.matches("^[+][a-zA-Z]{2}([(][0-9]{1,3}[)])?[0-9]{4,25}$"));
			}
			Assert.notNull(email);
			Assert.notNull(name);
			Assert.notNull(surname);
			Assert.notNull(address);
			Assert.notNull(picture);

			result.getUserAccount().setUsername(username);
			result.setName(name);
			result.setSurname(surname);
			result.setPhoneNumber(phoneNumber);
			result.setEmail(email);
			result.setAddress(address);
			result.setPicture(picture);
			result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(password, null));

			kindergartenService.save(result);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverkindergartenRegisterTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> true
			{
				"kindergartenTest", "kindergartenTest", "kindergartenTest", "kindergartenTestName", "kindergartenTestSurname", "+ES1234456", "kindergartenTest@kindergartenTest.com", "Calle de test", "http://www.picture.com", null
			},
			// Todo vacio --> false
			{
				null, null, null, null, null, null, null, null, null, IllegalArgumentException.class
			},
			// Las contraseñas no coinciden -> false
			{
				"kindergartenTest", "kindergartenTest", "12345", "kindergartenTestName", "kindergartenTestSurname", "+ES1234456", "kindergartenTest@kindergartenTest.com", "Calle de test", "http://www.picture.com", IllegalArgumentException.class
			},
			// Todos los campos completados, excepto el telefono -> true
			{
				"kindergartenTest", "kindergartenTest", "kindergartenTest", "kindergartenTestName", "kindergartenTestSurname", null, "kindergartenTest@kindergartenTest.com", "Calle de test", "http://www.picture.com", null
			},
			// Patrón del telefono erroneo -> false
			{
				"kindergartenTest", "kindergartenTest", "kindergartenTest", "kindergartenTestName", "kindergartenTestSurname", "1234456", "kindergartenTest@kindergartenTest.com", "Calle de test", "http://www.picture.com", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.kindergartenRegisterTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}

}
