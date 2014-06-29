package pt.davidafsilva.jtrakt.internal.response;

/**
 * This exception shall be thrown whenever an error object is detected while
 * deserializing an arbitrary object, which most likely points to a query
 * that returned an error result.
 *
 * @author David Silva
 */
class NoResultError extends Error {

    /**
     * Default constructor
     */
    public NoResultError() {
        super();
    }
}
