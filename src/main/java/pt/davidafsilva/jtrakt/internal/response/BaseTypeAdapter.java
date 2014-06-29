package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * This is the base type adapter for all of the deserialization adapters. It
 * provides a set of common functionality between all of them.
 *
 * @author David Silva
 */
abstract class BaseTypeAdapter<T> extends TypeAdapter<T> {

    // logger
    protected Logger logger = Logger.getLogger(getClass().getSimpleName());

    // gson object
    protected final Gson gson;

    /**
     * Default constructor for the type adapter
     *
     * @param gson
     *         the GSON object
     */
    BaseTypeAdapter(final Gson gson) {
        this.gson = gson;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void write(final JsonWriter out, final T value)
            throws IOException {
        // we don't need write(), for now at least..
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final T read(final JsonReader in) throws IOException {
        try {
            logger.fine(String.format("reading object: %s..",
                                      getClass().getSimpleName()
                                                .replace("TypeAdapter", "")));
            final JsonToken token = in.peek();
            final T obj;
            switch (token) {
                case NULL:
                    obj = null;
                    in.nextNull();
                    break;
                default:
                    obj = createInstance();
                    handleToken(in, token, obj);
                    break;
            }

            onReadFinished(obj);

            return obj;
        } catch (NoResultError err) {
            return getObjectOnResultError();
        }
    }

    /**
     * This method is called whenever a read is {@code successfully} finalized.
     *
     * @param object
     *         the read object
     */
    void onReadFinished(final T object) {
        // do nothing, by default
    }

    /**
     * <p>
     * Returns the object to be returned by the type adapter
     * whenever a JSON error message has been detected by the
     * current type adapter.
     * </p>
     *
     * By default, this method returns {@code null}.
     *
     * @return the object to return when a result error is detected
     */
    T getObjectOnResultError() {
        return null;
    }

    // abstract methods

    /**
     * Handles the given JSON token and applies the changes to the
     * object being deserialized, denoted by {@code obj}.
     *
     * @param in
     *         the JSON reader stream where to read the value from
     * @param token
     *         the JSON token
     * @param obj
     *         the concrete object instance that is being deserialized
     * @throws IOException
     *         if an errors occurs while reading the data from the JSON stream
     * @throws IllegalStateException
     *         if an invalid JSON token is received
     * @throws NoResultError
     *         if an error response is detected while parsing the JSON tokens
     */
    abstract void handleToken(final JsonReader in, final JsonToken token,
                              final T obj)
            throws IOException;

    /**
     * Creates and returns a specific instance of the object type
     * being deserialized.
     *
     * @return the new object instance
     */
    abstract T createInstance();


    // auxiliary methods

    /**
     * Reads an object of the given type from the JSON reader stream.
     *
     * @param reader
     *         the JSON stream
     * @param clazz
     *         the class of the object
     * @param <C>
     *         the type of the object
     * @return the read object
     * @throws IOException
     *         if an errors occurs while reading the data from the JSON stream
     */
    <C> C readObject(final JsonReader reader, final Class<C> clazz)
            throws IOException {
        return gson.getAdapter(clazz).nullSafe().read(reader);
    }

}
