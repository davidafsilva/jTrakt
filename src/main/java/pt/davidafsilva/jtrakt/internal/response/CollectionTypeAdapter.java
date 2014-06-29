package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * The collection deserialization entity. It handles the deserialization of
 * collection related objects.
 *
 * @author David Silva
 */
final class CollectionTypeAdapter<E> extends BaseTypeAdapter<Collection<E>> {

    // the collection element type adapter
    private final TypeAdapter<E> elementTypeAdapter;

    // the collection constructor
    private final Supplier<Collection<E>> constructor;

    /**
     * Default constructor for the collection adapter
     *
     * @param gson
     *         the GSON object
     * @param elementTypeAdapter
     *         the element type adapter
     * @param constructor
     *         the collection instantiation constructor
     */
    public CollectionTypeAdapter(final Gson gson, final TypeAdapter<E> elementTypeAdapter,
                                 final Supplier<Collection<E>> constructor) {
        super(gson);
        this.elementTypeAdapter = elementTypeAdapter;
        this.constructor = constructor;
    }

    @Override
    void handleToken(final JsonReader in, final JsonToken token, final Collection<E> obj) throws IOException {
        switch (token) {
            case BEGIN_ARRAY:
                // handle the collection
                in.beginArray();
                while (in.hasNext()) {
                    final E element = elementTypeAdapter.read(in);
                    if (element != null) {
                        obj.add(element);
                    }
                }
                in.endArray();
                break;
            case BEGIN_OBJECT:
                // if an object is detected rather than an array
                // it's most likely due to an error result
                // let's check for the error then..
                int fieldsNumber = 0;
                int errorFieldsDetected = 0;

                in.beginObject();
                while (in.hasNext()) {
                    final String fieldName = in.nextName();

                    // detect error responses
                    if ("status".equals(fieldName)) {
                        errorFieldsDetected++;
                    } else if ("error".equals(fieldName)) {
                        errorFieldsDetected++;
                    }

                    in.skipValue();
                    fieldsNumber++;
                }
                in.endObject();

                if (errorFieldsDetected == fieldsNumber) {
                    throw new NoResultError();
                }

                // fall-through switch:
                // if an error wasn't detected, let's throw an exception
                // and abort the deserialization process.
            default:
                throw new IllegalStateException(String.format(
                        "Invalid JSON token received: %s", token.name()));
        }
    }

    @Override
    Collection<E> createInstance() {
        return constructor.get();
    }

    @Override
    Collection<E> getObjectOnResultError() {
        return createInstance();
    }
}
