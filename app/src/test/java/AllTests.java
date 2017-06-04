import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.plantr.tests.objects.TemperatureTest;

/**
 * 6/4/2017
 * Raman Maan
 *
 * Purpose: Encompasses all test suite classes for easy run
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({TemperatureTest.class})
public class AllTests {}
