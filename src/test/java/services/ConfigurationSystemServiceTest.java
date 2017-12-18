
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import utilities.AbstractTest;
import domain.ConfigurationSystem;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ConfigurationSystemServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	// Tests
	// ====================================================

	/*
	 * To check the validity of a new configuration system, the system must check the banner (url) and the lesson's tax.
	 * 
	 * En este test, se comprueban los valores introducidos en el panel de configuración del sistema.
	 * Para ello introducimos valores correctos e incorrectos para observa el comportamiento de la aplicación.
	 */

	public void configurationSystemEditTest(String username, String banner, Double lessonTax, final Class<?> expected) {
		Class<?> caught = null;

		try {

			authenticate(username);

			Assert.isTrue(lessonTax >= 0 && lessonTax != null);
			Assert.isTrue(ResourceUtils.isUrl(banner));

			ConfigurationSystem cs = configurationSystemService.getCS();
			configurationSystemService.save(cs);

			unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers
	// ===================================================

	@Test
	public void driverConfigurationSystemEditTest() {

		final Object testingData[][] = {
			// Alguien sin registrar/logueado -> false
			{
				null, "http://www.picture.com", 5.0, IllegalArgumentException.class
			},
			// Todo vacio --> false
			{
				null, null, null, NullPointerException.class
			},
			// Un administrador intenta cambiar la configuración -> true
			{
				"admin", "http://www.picture.com", 5.0, null
			},
			// Introducimos algo que no es una url -> false
			{
				"admin", "test", 5.0, IllegalArgumentException.class
			},
			// Introducimos una tax lesson negativa -> false
			{
				"admin", "test", -5.0, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.configurationSystemEditTest((String) testingData[i][0], (String) testingData[i][1], (Double) testingData[i][2], (Class<?>) testingData[i][3]);
	}

}
