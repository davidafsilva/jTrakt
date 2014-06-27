package pt.davidafsilva.jtrakt.client;

import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import java.util.Collections;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public final class TestResponse implements MockResponse {

    private final String jsonResponse;
    private final int httpCode;

    public TestResponse(final int httpCode, final String response) {
        this.jsonResponse = response;
        this.httpCode = httpCode;
    }

    @Override
    public Response response(Request request) {
        final TypedByteArray body = new TypedByteArray("application/json", jsonResponse.getBytes());
        return new Response(request.getUrl(), httpCode, "TEST", Collections.emptyList(), body);
    }
}
