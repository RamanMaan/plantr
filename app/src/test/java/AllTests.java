import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.plantr.tests.business.DatabaseAccessTest;
import comp3350.plantr.tests.business.DifficultyTest;
import comp3350.plantr.tests.business.objects.PlantTest;
import comp3350.plantr.tests.business.objects.TemperatureTest;
import comp3350.plantr.tests.persistence.StubDatabaseTest;

/**
 * 6/4/2017
 * Raman Maan
 * Purpose: Encompasses all test suite classes for easy run
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
		TemperatureTest.class, StubDatabaseTest.class, DatabaseAccessTest.class, DifficultyTest.class, PlantTest.class
})
public class AllTests {
}
