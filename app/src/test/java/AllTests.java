import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.plantr.tests.persistence.StubDatabaseTest;
import comp3350.plantr.tests.application.DatabaseAccessTest;
import comp3350.plantr.tests.business.DifficultyTest;
import comp3350.plantr.tests.objects.PlantTest;
import comp3350.plantr.tests.objects.TemperatureTest;

/**
 * 6/4/2017
 * Raman Maan
 * <p>
 * Purpose: Encompasses all test suite classes for easy run
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
		TemperatureTest.class, StubDatabaseTest.class, DatabaseAccessTest.class, DifficultyTest.class, PlantTest.class
})
public class AllTests {
}
