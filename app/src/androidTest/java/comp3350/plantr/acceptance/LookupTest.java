package comp3350.plantr.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;

import junit.framework.Assert;

import comp3350.plantr.presentation.LoginActivity;
import comp3350.plantr.presentation.PlantListAdapter;


public class LookupTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;

	public LookupTest() {
		super(LoginActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());

		// Disable this for full acceptance test
		// System.out.println("Injecting stub database.");
		// Services.createDataAccess(new DataAccessStub(Main.dbName));
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	@Test
	public void testSearchPlant() {
		solo.waitForActivity("LoginActivity");

		solo.enterText(0, "kevindam@plantr.io");
		solo.enterText(1, "plantr");

		solo.clickOnButton("Login");

		solo.waitForActivity("MainActivity");

		// click on menu button
		solo.clickOnImageButton(0);

		// click on plantepedia option
		solo.clickOnText("Plantipedia");

		// search for dracaena
		solo.enterText(0, "Dracaena");

		// check if Dracaena shows up from the search
		Assert.assertTrue(solo.searchText("Dracaena"));

		// check that Aloe does not show up after search
		Assert.assertFalse(solo.searchText("Aloe"));
		solo.clickInList(0);

		// check if we are on the Dracaena plantview screen
		Assert.assertTrue(solo.searchText("Dracaena"));
	}
}
