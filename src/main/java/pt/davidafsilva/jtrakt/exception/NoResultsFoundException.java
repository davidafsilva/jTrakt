package pt.davidafsilva.jtrakt.exception;

import retrofit.RetrofitError;

/**
 * This exception represents a service query that returned
 * an error with no results.
 * <br/>
 * Trakt API uses HTTP status codes to represent errors in some of their
 * responses, typically they'll send HTTP 400 (bad request) or HTTP 404
 * (not found) where queried with wrong or not found information.
 *
 * @author David Silva
 */
public final class NoResultsFoundException extends RuntimeException {

    /**
     * Default constructor.
     *
     * Constructs a new NoResultsFoundException with no cause and no message.
     */
    public NoResultsFoundException() {
        super();
    }

    /**
     * Constructs a new NoResultsFoundException with the
     * underlying error cause.
     *
     * @param cause
     *         the original retrofit error
     */
    public NoResultsFoundException(RetrofitError cause) {
        super(cause);
    }
}
