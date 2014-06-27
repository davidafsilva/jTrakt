package pt.davidafsilva.jtrakt.client;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class ClientBuilder {

    private final Map<String, MockResponse> responseMap = new HashMap<>();

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
        private int httpCode = HttpURLConnection.HTTP_OK;
        private StringBuilder queryParameters;

        private PathResponsePairBuilder(final String path) {
            this.path = path;
        }

        public PathResponsePairBuilder code(final int httpCode) {
            this.httpCode = httpCode;
            return this;
        }

        public PathResponsePairBuilder withParameter(final String key, final String value) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);

            if (queryParameters == null) {
                queryParameters = new StringBuilder();
            } else {
                queryParameters.append("&");
            }
            queryParameters.append(key);
            queryParameters.append("=");
            try {
                queryParameters.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // should not happen, ever!
                queryParameters.append(value);
            }
            return this;
        }

        private String getResponseKey() {
            final StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(path);
            if (queryParameters != null) {
                keyBuilder.append("?");
                keyBuilder.append(queryParameters.toString());
            }

            return keyBuilder.toString();
        }

        public ClientBuilder reply(final String jsonResponse) {
            Objects.requireNonNull(jsonResponse);
            responseMap.put(getResponseKey(), new TestResponse(httpCode, jsonResponse));
            return ClientBuilder.this;
        }
    }

}
