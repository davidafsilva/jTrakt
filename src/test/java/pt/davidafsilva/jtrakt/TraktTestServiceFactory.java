package pt.davidafsilva.jtrakt;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pt.davidafsilva.jtrakt.client.MockClient;
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
public enum TraktTestServiceFactory {
	INSTANCE;

	public TraktTvService createTvService(final MockClient client) {
		final RequestInterceptor requestInterceptor = requestFacade -> {
			// request parameters
			requestFacade.addPathParam("apiKey", "apiKey");

			// clean up thread cache
			DeserializationCache.INSTANCE.clean();
		};

		final Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
               .registerTypeAdapterFactory(ObjectFactory.INSTANCE)
               .create();

		return new RestAdapter.Builder().setEndpoint("https://api.trakt.tv")
				.setConverter(new GsonConverter(gson))
				.setRequestInterceptor(requestInterceptor)
				.setClient(client)
				.build().create(TraktTvService.class);
	}
}
