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
	public void testViewGarden()
	{
		solo.waitForActivity("LoginActivity");
		solo.enterText(1, "kevindam@plantr.io");
		solo.enterText(0, "plantr");
		solo.clickOnButton("Login");
		solo.waitForActivity("MainActivity");
		// check if the personal plants are there - should have two with names "Pupper" and "Snek"
		Assert.assertTrue(solo.searchText("Pupper the Peperomia"));
		Assert.assertTrue(solo.searchText("Can-I-Get-A"));
	}

	@Test
	public void testViewPersonalPlant()
	{
		solo.waitForActivity("LoginActivity");
		solo.enterText(1, "kevindam@plantr.io");
		solo.enterText(0, "plantr");
		solo.clickOnButton("Login");
		solo.waitForActivity("MainActivity");
		Assert.assertTrue(solo.searchText("Pupper the Peperomia"));
		solo.clickOnText("Pupper the Peperomia");
		Assert.assertTrue(solo.searchText("Pupper the Peperomia"));
		Assert.assertTrue(solo.searchText("Last Time Watered:"));
	}


}
