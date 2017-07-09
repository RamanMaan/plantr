package comp3350.plantr.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;

import static org.junit.Assert.fail;

public class UserManagerTest {

	@Before
	public void startUp() throws DatabaseStartFailureException {
		DatabaseAccess.openStub();
	}

	@Test
	public void testLoginUser_WrongPassword() {
		try {
			UserManager.loginUser("du@plantr.io", "wrong password");
			fail("Incorrect password was provided, should have failed");
		} catch (UserLoginException e) {
		}
	}

	@Test
	public void testLoginUser_NonExistantUser() {
		try {
			UserManager.loginUser("x@x.io", "");
			fail("Invalid User was provided, should have failed");
		} catch (UserLoginException e) {
		}
	}

	@Test
	public void testLoginUser_InvalidValues() {
		try {
			UserManager.loginUser(null, null);
			fail("Incorrect password was provided, should have failed");
		} catch (UserLoginException e) {
		}
	}
}
