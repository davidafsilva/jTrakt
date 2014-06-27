package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO: change me
 *
 * @author David Silva
 */
abstract class ObjectTypeAdapter<T> extends BaseTypeAdapter<T> {

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	ObjectTypeAdapter(final Gson gson) {
		super(gson);
	}

    /**
     * {@inheritDoc}
     */
	protected void handleToken(JsonReader in, JsonToken token, T obj) throws IOException {
        int fieldsNumber = 0;
        int errorFieldsDetected = 0;
		switch (token) {
			case BEGIN_OBJECT:
				in.beginObject();
				while (in.hasNext()) {
                    final String fieldName = in.nextName();

                    // detect error responses
                    if ("status".equals(fieldName)) {
                        errorFieldsDetected++;
                    } else if ("error".equals(fieldName)) {
                        errorFieldsDetected++;
                    }

					updateFieldValue(obj, fieldName, in);
                    fieldsNumber++;
				}
				in.endObject();
				break;
			default:
				throw new IllegalStateException(String.format("Invalid JSON token received: %s", token.name()));
		}

        if (errorFieldsDetected == fieldsNumber) {
            logger.warning("error detected!");
            throw new NoResultError();
        }
	}

	// abstract methods

	/**
	 * Updates the given field value on the object instance
	 *
	 * @param object
	 * 		the object where to updated the field value
	 * @param fieldName
	 * 		the name of the field being updated
	 * @param in
	 * 		the JSON reader stream where to read the value from
	 */
	abstract void updateFieldValue(T object, String fieldName, JsonReader in) throws IOException;

	// auxiliary methods

	String readString(final JsonReader reader) throws IOException {
		return readObject(reader, String.class);
	}

	Integer readInt(final JsonReader reader) throws IOException {
		return readObject(reader, int.class);
	}

	Boolean readBoolean(final JsonReader reader) throws IOException {
		return readObject(reader, boolean.class);
	}

	Long readLong(final JsonReader reader) throws IOException {
		return readObject(reader, long.class);
	}

	LocalDateTime readDateTimeTimestamp(final JsonReader reader, final ZoneOffset offset) throws IOException {
		final Long timestamp = readLong(reader);
		return timestamp == null ? null : LocalDateTime.ofEpochSecond(timestamp, 0, offset);
	}

	<C> Set<C> readSet(final JsonReader reader, final Class<C> clazz) throws IOException {
		Set<C> set = new HashSet<>();
		readCollection(reader, clazz, set);
		return set;
	}

	<C> List<C> readList(final JsonReader reader, final Class<C> clazz) throws IOException {
		List<C> list = new ArrayList<>();
		readCollection(reader, clazz, list);
		return list;
	}

	<C> void readCollection(final JsonReader reader, final Class<C> clazz, Collection<C> destiny) throws IOException {
		reader.beginArray();
		while (reader.hasNext()) {
			destiny.add(gson.getAdapter(clazz).read(reader));
		}
		reader.endArray();
	}
}
