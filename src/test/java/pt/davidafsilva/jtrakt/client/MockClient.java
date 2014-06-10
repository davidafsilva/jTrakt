package pt.davidafsilva.jtrakt.client;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class MockClient implements Client {

	private final Map<String, String> responseMap;

	MockClient(final Map<String, String> responseMap) {
		this.responseMap = responseMap;
	}

	@Override
	public Response execute(Request request) throws IOException {
		final URI uri = URI.create(request.getUrl());

		final String response = responseMap.get(uri.getPath());
		if (response == null) {
			throw new IllegalStateException(String.format("expected a request path: %s", uri.getPath()));
		}

		final TypedByteArray body = new TypedByteArray("application/json", response.getBytes());
		return new Response(request.getUrl(), HttpURLConnection.HTTP_OK, "OK", Collections.emptyList(), body);
	}
}
