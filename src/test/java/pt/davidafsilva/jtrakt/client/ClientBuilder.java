package pt.davidafsilva.jtrakt.client;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class ClientBuilder {

	private final Map<String, String> responseMap = new HashMap<>();


	public PathResponsePairBuilder whenRequest(final String path) {
		if (path == null) {
			throw new NullPointerException("path cannot be null");
		}
		return new PathResponsePairBuilder(path);
	}

	public MockClient build() {
		if (responseMap.isEmpty()) {
			throw new IllegalStateException("no responses were mapped for the client");
		}
		return new MockClient(responseMap);
	}

	public class PathResponsePairBuilder {
		private String path;

		private PathResponsePairBuilder(final String path) {
			this.path = path;
		}

		public ClientBuilder reply(final String jsonResponse) {
			if (jsonResponse == null) {
				throw new NullPointerException("path cannot be null");
			}
			responseMap.put(path, jsonResponse);
			return ClientBuilder.this;
		}
	}

}
