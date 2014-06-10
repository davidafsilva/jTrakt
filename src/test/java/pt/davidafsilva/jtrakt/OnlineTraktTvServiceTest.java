package pt.davidafsilva.jtrakt;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class OnlineTraktTvServiceTest extends TraktTvServiceTest {

	@Override
	TraktTvService setupService() {
		return TraktServiceFactory.INSTANCE.createTvService("0473e5d7231ddeab7d1262a9dadaa457");
	}
}
