package pt.davidafsilva.jtrakt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is the suite of jUnit tests
 *
 * @author David Silva
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({OfflineTraktTvServiceTest.class, OnlineTraktTvServiceTest.class})
public class AllTests {
}
