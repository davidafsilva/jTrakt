package pt.davidafsilva.jtrakt;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	public TraktTvService createTvService(final String apiKey) {
		return createRestAdapter(apiKey).create(TraktTvService.class);
	}

	private RestAdapter createRestAdapter(final String apiKey) {
		final RequestInterceptor requestInterceptor = requestFacade -> requestFacade.addPathParam("apiKey", apiKey);
		final Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.registerTypeAdapterFactory(ObjectFactory.INSTANCE)
				.create();

		return new RestAdapter.Builder()
				.setEndpoint("https://api.trakt.tv")
				.setConverter(new GsonConverter(gson))
				//.setErrorHandler(new TraktErrorHandler())
				.setRequestInterceptor(requestInterceptor)
				.build();
	}

}
