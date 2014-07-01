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
 * The factory for the available Trakt services.<br/>
 * All services are created upon request, as such please consider caching
 * the instance of a given service if you intend to use it as a singleton.
 *
 * @author David Silva
 */
public class TraktServiceFactory {

    // singleton holder
    private static final class Holder {
        private static final TraktServiceFactory instance =
                new TraktServiceFactory();
    }

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
        return Holder.instance;
    }

    /**
     * Deserialization safety
     */
    Object readResolve() {
        return Holder.instance;
    }

    // static properties
    private static final String NAME =
            TraktServiceFactory.class.getPackage().getImplementationTitle();
    private static final String VERSION =
            TraktServiceFactory.class.getPackage().getImplementationVersion();
    private static final String USER_AGENT = NAME + "/" + VERSION;

    /**
     * <p>
     * Creates a {@link TraktTvService} instance in order to issue TV related
     * requests to the remote Trakt server host.
     * </p>
     * <p>
     * The service requires an API key to be used. <br/>
     * In order to acquire your API key please create an account at
     * <a href="http://trakt.tv">trakt.tv</a>.
     * </p>
     *
     * @param apiKey
     *         the service API key
     * @return the Trakt TV service instance
     */
    public TraktTvService createTvService(final String apiKey) {
        return createRestAdapter(apiKey).create(TraktTvService.class);
    }

    /**
     * Creates the rest adapter with the given API key
     *
     * @param apiKey
     *         the service API key
     * @return the built rest adapter
     */
    private RestAdapter createRestAdapter(final String apiKey) {
        // build the JSON mapping
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(
                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                           .registerTypeAdapterFactory(
                                                   ObjectTypeAdapterFactory
                                                           .INSTANCE)
                                           .registerTypeAdapterFactory(
                                                   CollectionTypeAdapterFactory.INSTANCE)
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

    /**
     * Returns the HTTP header "User-agent" to be used in the HTTP request
     *
     * @return the "User-agent" value
     */
    String getUserAgent() {
        return USER_AGENT;
    }

    /**
     * Returns the request interceptor to be applied in all the service
     * request.
     *
     * @param apiKey
     *         the service API key
     * @return the request interceptor
     */
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

    /**
     * This method is called in order to configure additional parameters
     * in the rest adapter.
     * <br/>
     * This method is empty by default.
     *
     * @param builder
     *         the current rest adapter builder
     */
    void configureRestAdapter(final RestAdapter.Builder builder) {
        // empty by default
    }

    /**
     * The Trakt HTTP errors handler
     */
    private static final class TraktErrorHandler implements ErrorHandler {

        @Override
        public Throwable handleError(final RetrofitError cause) {
            Throwable ret = cause;
            // http://trakt.tv/api-docs/errors
            switch (cause.getResponse().getStatus()) {
                case 400: // bad request
                case 401: // unauthorized
                case 404: // not found
                case 503: // Service Unavailable
                    if (cause.getResponse().getBody() != null) {
                        try {
                            final Scanner bodyScanner = new Scanner(
                                    cause.getResponse().getBody().in(), "UTF-8")
                                    .useDelimiter("\\A");
                            final String body = bodyScanner.hasNext() ?
                                                bodyScanner.next() :
                                                null;
                            if (body != null && body.length() > 1) {
                                char first = body.charAt(0);
                                char last = body.charAt(body.length() - 1);
                                if ((first == '[' && last == ']') ||
                                    (first == '{' && last == '}')) {
                                    // TODO: extract error message if it's an
                                    // object to give a more meaningful error
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
