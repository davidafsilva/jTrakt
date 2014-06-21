package pt.davidafsilva.jtrakt;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pt.davidafsilva.jtrakt.internal.cache.DeserializationCache;
import pt.davidafsilva.jtrakt.internal.response.ObjectFactory;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public enum TraktServiceFactory {
	INSTANCE;

	private static final String NAME = TraktServiceFactory.class.getPackage().getImplementationTitle();
	private static final String VERSION = TraktServiceFactory.class.getPackage().getImplementationVersion();
	private static final String USER_AGENT = NAME + "/" + VERSION;

	public TraktTvService createTvService(final String apiKey) {
		return createRestAdapter(apiKey).create(TraktTvService.class);
	}

	private RestAdapter createRestAdapter(final String apiKey) {
		// setup the request interceptor
		final RequestInterceptor requestInterceptor = requestFacade -> {
			// request header
			requestFacade.addHeader("User-Agent", USER_AGENT);
			requestFacade.addHeader("DNT", "1");

			// request parameters
			requestFacade.addPathParam("apiKey", apiKey);

			// clean up thread cache
			DeserializationCache.INSTANCE.clean();
		};

		// build the JSON mapping
		final Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.registerTypeAdapterFactory(ObjectFactory.INSTANCE)
				.create();

		// build the REST adapter
		return new RestAdapter.Builder()
				.setEndpoint("https://api.trakt.tv")
				.setConverter(new GsonConverter(gson))
				//.setErrorHandler(new TraktErrorHandler())
				.setRequestInterceptor(requestInterceptor)
				.build();
	}

}
