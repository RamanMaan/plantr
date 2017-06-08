import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.plantr.tests.StubDatabaseTest;
import comp3350.plantr.tests.application.DatabaseAccessTest;
import comp3350.plantr.tests.objects.TemperatureTest;
import comp3350.plantr.tests.objects.PlantTest;

/**
 * 6/4/2017
 * Raman Maan
 *
 * Purpose: Encompasses all test suite classes for easy run
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({PlantTest.class, TemperatureTest.class, StubDatabaseTest.class, DatabaseAccessTest.class})
public class AllTests {}
