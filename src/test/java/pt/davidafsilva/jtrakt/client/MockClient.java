package pt.davidafsilva.jtrakt.client;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class MockClient implements Client {

	private final Map<String, MockResponse> responseMap;

	MockClient(final Map<String, MockResponse> responseMap) {
		this.responseMap = responseMap;
	}

	@Override
	public Response execute(Request request) throws IOException {
		final URI uri = URI.create(request.getUrl());

		final MockResponse response = responseMap.get(getKey(uri));
		if (response == null) {
			throw new IllegalStateException(String.format("expected a request path: %s", uri.getPath()));
		}

        return response.response(request);
	}

    private String getKey(URI uri) {
        final StringBuilder sb = new StringBuilder();
        sb.append(uri.getPath());
        if (uri.getQuery() != null && !uri.getQuery().isEmpty()) {
            sb.append("?");
            sb.append(uri.getQuery());
        }

        return sb.toString();
    }
}
