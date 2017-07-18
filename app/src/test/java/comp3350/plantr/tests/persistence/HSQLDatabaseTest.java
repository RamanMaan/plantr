package comp3350.plantr.tests.persistence;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.widget.Toast;

import junit.framework.TestCase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.Garden;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.User;

/**
 * Created by Austin on 2017-07-11.
 */

public class HSQLDatabaseTest extends TestCase {

	public HSQLDatabaseTest(String arg0) {
		super(arg0);
	}

	public static void testAccess() {
		Garden testGarden = new Garden();
		PersonalPlant pPlant;
		ArrayList<PersonalPlant> personalPlantCollection = new ArrayList<PersonalPlant>();
		User testUser;



		try {
				DatabaseAccess.open();
				testGarden.addPlants(DatabaseAccess.getDatabaseAccess().getAllPersonalPlants());
				assertNotNull(testGarden);

			try {
				UserManager.loginUser("du@plantr.io", "plantr");
				testUser = UserManager.getUser();
				//test retrieval of plants
				pPlant = testGarden.getPersonalPlantById(0);
				assertNotNull(pPlant);
				assertEquals(0, pPlant.getID());

				//test addition of a plant
				pPlant = new PersonalPlant(pPlant.getType(), "Alex", 5, new Date(20170610), testUser);
				assertEquals(true, testGarden.addPlant(pPlant));
				assertNotNull(testGarden.getPersonalPlantById(5));

				//test removal of a plant
				assertEquals(true, testGarden.removePersonalPlant(pPlant.getID()));
				assertNull(testGarden.getPersonalPlantById(pPlant.getID()));

				//test addition of a plant alternative way
				personalPlantCollection.add(pPlant);
				assertEquals(true, testGarden.addPlants(personalPlantCollection));
				assertNotNull(testGarden.getPersonalPlantById(pPlant.getID()));

				try {
					DatabaseAccess.close();
				} catch(DatabaseCloseFailureException e) {
					System.out.println("Encountered an error closing the Database in HSQLDatabaseTest class");
					e.printStackTrace();
				}

			} catch (UserLoginException e) {
				System.out.println("Encountered an error logging in default user");
				e.printStackTrace();
			}

		} catch (DatabaseStartFailureException | SQLException | UserLoginException e) {
			System.out.println("Encountered an error in HSQLDatabaseTest class");
			e.printStackTrace();
		}
	}
}
