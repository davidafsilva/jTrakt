package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import pt.davidafsilva.jtrakt.model.Genre;

import java.io.IOException;

/**
 * Deserialization entity for {@link Genre} objects.
 *
 * @author David Silva
 */
final class GenreTypeAdapter extends ObjectTypeAdapter<Genre> {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	GenreTypeAdapter(Gson gson) {
		super(gson);
	}

	private enum Fields {
		NAME, SLUG
	}

	@Override
	Genre createInstance() {
		return new Genre();
	}

	@Override
	protected void handleToken(JsonReader in, JsonToken token, Genre obj) throws IOException {
		switch (token) {
			case STRING:
				obj.setName(in.nextString());
				obj.setSlung(obj.getName());
				break;
			case BEGIN_OBJECT:
				super.handleToken(in, token, obj);
				break;
			default:
				throw new IllegalStateException(String.format("Invalid JSON token received: %s", token.name()));
		}

	}

	@Override
	void updateFieldValue(Genre object, String fieldName, JsonReader in) throws IOException {
		final String properFieldName = fieldName.toUpperCase();
		Fields field = null;
		try {
			field = Fields.valueOf(properFieldName);
		} catch (IllegalArgumentException e) {
			logger.warning(String.format("Field %s not found.", fieldName));
		}

		if (field != null) {
			switch (field) {
				case NAME:
					object.setName(readString(in));
					break;
				case SLUG:
					object.setSlung(readString(in));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
