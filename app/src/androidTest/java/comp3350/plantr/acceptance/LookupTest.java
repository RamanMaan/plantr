package comp3350.plantr.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;

import junit.framework.Assert;

import comp3350.plantr.presentation.LoginActivity;


public class LookupTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;

	public LookupTest()
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
	public void searchPlant()
	{
		solo.waitForActivity("LoginActivity");

		solo.enterText(1, "kevindam@plantr.io");
		solo.enterText(0, "plantr");

		solo.clickOnButton("Login");

		solo.waitForActivity("MainActivity");

		solo.clickOnImageButton(0); // click on menu button
		solo.clickOnText("Plantipedia"); // click on plantepedia option

		solo.waitForActivity("PlantipediaFragment");

		solo.assertCurrentActivity("Expected activity PlantipediaFragment", "PlantipediaFragment");

//		Assert.assertTrue(solo.searchText("400: Mary Bailey"));
//		solo.clickOnText("400: Mary Bailey");
//		Assert.assertTrue(solo.searchEditText("400"));
//		Assert.assertTrue(solo.searchEditText("Mary Bailey"));
//		Assert.assertTrue(solo.searchEditText("Off Campus"));
//
//		solo.clearEditText(1);
//		solo.enterText(1, "Mary Bucket");
//		solo.clearEditText(2);
//		solo.enterText(2, "Somewhere Else");
//		solo.clickOnButton("Update");
//
//		solo.goBack();
//
//		solo.waitForActivity("HomeActivity");
//		solo.clickOnButton("Students");
//		solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");
//
//		solo.waitForText("400: Mary Bucket");
//		Assert.assertTrue(solo.searchText("400: Mary Bucket"));
//		solo.clickOnText("400: Mary Bucket");
//		Assert.assertTrue(solo.searchEditText("400"));
//		Assert.assertTrue(solo.searchEditText("Mary Bucket"));
//		Assert.assertTrue(solo.searchEditText("Somewhere Else"));
//
//		// clean up
//		solo.clearEditText(1);
//		solo.enterText(1, "Mary Bailey");
//		solo.clearEditText(2);
//		solo.enterText(2, "Off Campus");
//		solo.clickOnButton("Update");
	}
}
