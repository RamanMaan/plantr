package comp3350.plantr.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;

import junit.framework.Assert;

import comp3350.plantr.presentation.LoginActivity;
import comp3350.plantr.presentation.PlantListAdapter;


public class TrackPersonalPlantsTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;

	public TrackPersonalPlantsTest()
	{
		super(LoginActivity.class);
	}

	public void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());

		// Disable this for full acceptance test
		// System.out.println("Injecting stub database.");
		// Services.createDataAccess(new DataAccessStub(Main.dbName));
	}

	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}

	@Test
	public void testDeleteFromGarden(){
		navigateToPersonalPlant();

		Assert.assertTrue(solo.searchButton("Remove From Garden"));
		solo.clickOnButton("Remove From Garden");

		Assert.assertTrue(solo.searchButton("Remove"));
		Assert.assertTrue(solo.searchButton("Cancel"));

		//cancel function works
		//solo.clickOnButton("Cancel");
		//solo.assertCurrentActivity("Expected activity PersonalPlantView", "PersonalPlantView");


		//Assert.assertTrue(solo.searchButton("Remove From Garden"));
		//solo.clickOnButton("Remove From Garden");

		//Delete button
		solo.clickOnButton("Remove");
		solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
	}

	public void testEditPersonalPlants(){
		navigateToPersonalPlant();

		solo.clickOnImageButton(1);

		Assert.assertTrue(solo.searchButton("Water"));
		Assert.assertTrue(solo.searchButton("Cancel"));

		//cancel function works
		solo.clickOnButton("Cancel");
		solo.assertCurrentActivity("Expected activity PersonalPlantView", "PersonalPlantView");

		solo.clickOnImageButton(0);
		Assert.assertTrue(solo.searchButton("Water"));
		Assert.assertTrue(solo.searchButton("Cancel"));

		//Delete button
		solo.clickOnButton("Water");
		solo.assertCurrentActivity("Expected activity PersonalPlantView", "PersonalPlantView");
	}

	public void navigateToGarden()
	{
		login();
		//you start at the garden, so no need for further navigation
	}

	public void navigateToPersonalPlant()
	{
		login();

		solo.clickInList(0);
		solo.assertCurrentActivity("Expected activity MainActivity", "MainActivity");
	}

	public void login(){
		solo.waitForActivity("LoginActivity");

		//login credentials
		solo.enterText(1,"TEST_USER@plantr.io");
		solo.enterText(0, "plantr");

		solo.clickOnButton("Login");

		solo.waitForActivity("MainActivity");
	}
}
