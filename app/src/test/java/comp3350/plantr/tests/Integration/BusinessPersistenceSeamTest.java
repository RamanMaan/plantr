package comp3350.plantr.tests.Integration;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


import java.util.Date;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.User;

/**
 * Created by Austin on 2017-07-10.
 */

public class BusinessPersistenceSeamTest extends TestCase {

	public BusinessPersistenceSeamTest(String arg0) {
		super(arg0);
	}

	@Before
	public void startUp() {
		try {
			DatabaseAccess.open();
		} catch (DatabaseStartFailureException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAccessGarden() {

			//test logging in as a user
			String correctEmail = "du@plantr.io";
			String correctPassword = "plantr";

			String badEmail = "something@somethingelse.com";
			String badPassword = "Notplantr";

			try {
				try {
					UserManager.loginUser(badEmail, badPassword);
				} catch(UserLoginException e) {
					assertNotNull(e);
				}

				UserManager.loginUser(correctEmail, correctPassword); //should accept
				assertEquals(correctEmail, UserManager.getUser().getEmail());

				UserManager.loginUser(correctEmail, correctPassword);


				//test adding a plant to a user's garden
				Garden testGarden = new Garden();
				User testUser = UserManager.getUser();
				testGarden.addPlant(new PersonalPlant(new Plant(0), "Test", 0, new Date(), testUser));

				assertNotNull(testGarden.getPersonalPlantById(0));

				//test removing a plant from a user's garden
				assertTrue(testGarden.removePersonalPlant(0));

				assertFalse(testGarden.removePersonalPlant(0));

			} catch (UserLoginException e) {
				e.printStackTrace();
			}
	}
}
