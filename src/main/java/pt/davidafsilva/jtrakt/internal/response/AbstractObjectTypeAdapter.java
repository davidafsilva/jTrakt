package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * TODO: change me
 *
 * @author David Silva
 */
abstract class AbstractObjectTypeAdapter<T> extends TypeAdapter<T> {

	protected Logger logger = Logger.getLogger(getClass().getSimpleName());

	protected final Gson gson;

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	AbstractObjectTypeAdapter(final Gson gson) {
		this.gson = gson;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void write(JsonWriter out, T value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
			writeObject(out, value);
		}
	}

	/**
	 * Writes the given (not-null) object to the JSON writer stream
	 *
	 * @param out
	 * 		the output stream
	 * @param value
	 * 		the object to be written
	 */
	@SuppressWarnings("unused")
	protected void writeObject(final JsonWriter out, final T value) {
		// we don't need write() for now..
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final T read(JsonReader in) throws IOException {
		logger.fine(String.format("reading object: %s..", getClass().getSimpleName().replace("TypeAdapter", "")));
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
	}

	/**
	 * This method is called whenever a read is indeed finalized.
	 *
	 * @param object
	 * 		the read object
	 */
	void onReadFinished(final T object) {
		// do nothing, by default
	}

	/**
	 * Handles the given JSON token
	 *
	 * @param in
	 * 		the JSON reader stream where to read the value from
	 * @param token
	 * 		the JSON token
	 * @param obj
	 * 		the concrete object instance
	 * @throws IOException
	 * 		if an errors occurs while reading the data from the JSON stream
	 * @throws IllegalStateException
	 * 		if an invalid JSON token is received
	 */
	protected void handleToken(JsonReader in, JsonToken token, T obj) throws IOException {
		switch (token) {
			case BEGIN_OBJECT:
				in.beginObject();
				while (in.hasNext()) {
					updateFieldValue(obj, in.nextName(), in);
				}
				in.endObject();
				break;
			default:
				throw new IllegalStateException(String.format("Invalid JSON token received: %s", token.name()));
		}
	}

	// abstract methods

	/**
	 * Creates and returns a specific instance of the object type
	 * being deserialized.
	 *
	 * @return the new object instance
	 */
	abstract T createInstance();

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

	<C> C readObject(final JsonReader reader, Class<C> clazz) throws IOException {
		return gson.getAdapter(clazz).nullSafe().read(reader);
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
