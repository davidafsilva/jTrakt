package pt.davidafsilva.jtrakt;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class OnlineTraktTvServiceTest extends TraktTvServiceTest {

	@Override
	TraktTvService setupService() {
		return TraktTestServiceFactory.getInstance().createTvService("0473e5d7231ddeab7d1262a9dadaa457");
	}

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Thread.sleep(500); /** sleep 1s **/;
    }
}
