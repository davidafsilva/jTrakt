package pt.davidafsilva.jtrakt;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pt.davidafsilva.jtrakt.exception.NoResultsFoundException;
import pt.davidafsilva.jtrakt.internal.cache.DeserializationCache;
import pt.davidafsilva.jtrakt.internal.response.CollectionTypeAdapterFactory;
import pt.davidafsilva.jtrakt.internal.response.ObjectTypeAdapterFactory;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

import java.io.IOException;
import java.util.Scanner;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TraktServiceFactory {

    // singleton
    private static final TraktServiceFactory instance = new TraktServiceFactory();

    /**
     * private constructor
     */
    TraktServiceFactory() {
    }

    /**
     * Returns the singleton instance of the service factory
     *
     * @return the service factory instance
     */
    public static TraktServiceFactory getInstance() {
        return instance;
    }

    /**
     * Deserialization safety
     */
    Object readResolve() {
        return instance;
    }

    private static final String NAME = TraktServiceFactory.class.getPackage().getImplementationTitle();
    private static final String VERSION = TraktServiceFactory.class.getPackage().getImplementationVersion();
    private static final String USER_AGENT = NAME + "/" + VERSION;

    public TraktTvService createTvService(final String apiKey) {
        return createRestAdapter(apiKey).create(TraktTvService.class);
    }

    private RestAdapter createRestAdapter(final String apiKey) {
        // build the JSON mapping
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                           .registerTypeAdapterFactory(ObjectTypeAdapterFactory.INSTANCE)
                                           .registerTypeAdapterFactory(CollectionTypeAdapterFactory.INSTANCE)
                                           .create();

        // build the REST adapter
        final RestAdapter.Builder builder = new RestAdapter.Builder();
        // setup basic rest adapter
        builder.setConverter(new GsonConverter(gson))
               .setRequestInterceptor(getRequestInterceptor(apiKey))
               .setEndpoint("https://api.trakt.tv")
               .setErrorHandler(new TraktErrorHandler());
        // configure additional settings
        configureRestAdapter(builder);

        return builder.build();
    }

    String getUserAgent() {
        return USER_AGENT;
    }

    RequestInterceptor getRequestInterceptor(final String apiKey) {
        return requestFacade -> {
            // request header
            requestFacade.addHeader("User-Agent", getUserAgent());
            requestFacade.addHeader("DNT", "1");
            requestFacade.addHeader("apiKey", apiKey);

            // request parameters
            requestFacade.addPathParam("apiKey", apiKey);

            // clean up thread cache
            DeserializationCache.INSTANCE.clean();
        };
    }

    void configureRestAdapter(final RestAdapter.Builder builder) {
        // empty by default
    }

    private static final class TraktErrorHandler implements ErrorHandler {

        @Override
        public Throwable handleError(final RetrofitError cause) {
            Throwable ret = cause;
            switch (cause.getResponse().getStatus()) {
                case 404: // not found
                case 400: // bad request
                    if (cause.getResponse().getBody() != null) {
                        try {
                            final Scanner bodyScanner =
                                    new Scanner(cause.getResponse().getBody().in(), "UTF-8").useDelimiter("\\A");
                            final String body = bodyScanner.hasNext() ? bodyScanner.next() : null;
                            if (body != null && body.length() > 1) {
                                char first = body.charAt(0);
                                char last = body.charAt(body.length() - 1);
                                if ((first == '[' && last == ']') || (first == '{' && last == '}')) {
                                    // TODO: extract error message if it's an object to give a more meaningful error
                                    ret = new NoResultsFoundException(cause);
                                }
                            }
                        } catch (IOException e) {
                            // ignore it, return the original cause
                        }
                    }
                    break;
            }

            return ret;
        }
    }

}
