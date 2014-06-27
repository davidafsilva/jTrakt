package pt.davidafsilva.jtrakt;

import pt.davidafsilva.jtrakt.client.MockClient;
import retrofit.RestAdapter;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public final class TraktTestServiceFactory extends TraktServiceFactory {

    private static final String USER_AGENT = "jTrakt/1.0";

    // not thread-safe, but tests are not multi-threaded.
    private MockClient client;

    public TraktTvService createTvService(final String apiKey, final MockClient client) {
        this.client = client;
        return super.createTvService(apiKey);
    }

    public static TraktTestServiceFactory getInstance() {
        return new TraktTestServiceFactory();
    }

    @Override
    String getUserAgent() {
        return USER_AGENT;
    }

    @Override
    void configureRestAdapter(final RestAdapter.Builder builder) {
        super.configureRestAdapter(builder);
        if (client != null) {
            builder.setClient(client);
        }
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
    }
}
