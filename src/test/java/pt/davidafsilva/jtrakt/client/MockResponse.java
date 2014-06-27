package pt.davidafsilva.jtrakt.client;

import retrofit.client.Request;
import retrofit.client.Response;

/**
 * TODO: change me
 *
 * @author David Silva
 */
@FunctionalInterface
public interface MockResponse {

    Response response(Request request);
}
