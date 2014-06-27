package pt.davidafsilva.jtrakt.exception;

import retrofit.RetrofitError;

/**
 * TODO: change me
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
