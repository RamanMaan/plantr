package comp3350.plantr.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.plantr.presentation.LoginActivity;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

public class ViewInformationOnPlantsTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;

	public ViewInformationOnPlantsTest() {
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

	// Please note again that this is not a complete set of acceptance tests

	@Test
	public void testPlantInformation() {
		navigateToPlant();

		Assert.assertTrue(solo.searchText("Aloe"));
		Assert.assertTrue(solo.searchText("Difficulty: HARD"));
		Assert.assertTrue(solo.searchText("Optimal Temperature: 21.0°C - 23.0°C"));
		Assert.assertTrue(solo.searchText("Watering Frequency: Every 23 day"));
	}

	@Test
	public void testAddToGarden() {
		navigateToPlant();

		Assert.assertTrue(solo.searchButton("Add to Garden"));
		solo.clickOnButton("Add to Garden");

		Assert.assertTrue(solo.searchText("Enter a name for your plant:"));
		Assert.assertTrue(solo.searchButton("OK"));
		Assert.assertTrue(solo.searchButton("Cancel"));
		Assert.assertTrue(solo.searchEditText(""));

		solo.enterText(0, "KEATON'S PLANTR TEST");

		solo.clickOnButton("Cancel");

		Assert.assertTrue(solo.searchButton("Add to Garden"));
		solo.clickOnButton("Add to Garden");

		solo.enterText(0, "KEATON'S PLANTR TEST");

		solo.clickOnButton("OK");
	}

	public void navigateToPlant() {
		solo.waitForActivity("LoginActivity");

		//login credentials
		solo.enterText(1, "TEST_USER@plantr.io");
		solo.enterText(0, "plantr");

		solo.clickOnButton("Login");

		solo.waitForActivity("MainActivity");
		solo.clickOnImageButton(0);

		solo.clickOnText("Plantipedia");

		solo.clickInList(0);

		solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
	}
}
