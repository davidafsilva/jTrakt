package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import pt.davidafsilva.jtrakt.model.Actor;

import java.io.IOException;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class ActorTypeAdapter extends ObjectTypeAdapter<Actor> {

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	ActorTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		NAME, CHARACTER, IMAGES;
	}

	@Override
	Actor createInstance() {
		return new Actor();
	}

	@Override
	void updateFieldValue(final Actor object, final String fieldName, final JsonReader in) throws IOException {
		final String properFieldName = fieldName.toUpperCase();
		Fields field = null;
		try {
			field = Fields.valueOf(properFieldName);
		} catch (IllegalArgumentException e) {
			in.skipValue();
			logger.warning(String.format("Field %s not found.", fieldName));
		}

		if (field != null) {
			switch (field) {
				case NAME:
					object.setName(readString(in));
					break;
				case CHARACTER:
					object.setCaracter(readString(in));
					break;
				case IMAGES:
					//TODO: might change this if more image types are actually supported
					switch (in.peek()) {
						case BEGIN_OBJECT:
							in.beginObject();
							final JsonToken peek = in.peek();
							if (peek == JsonToken.NAME && "headshot".equals(in.nextName())) {
								object.setPictureUrl(readString(in));
							} else {
								in.skipValue();
							}
							in.endObject();
							break;
						default:
							break;
					}
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
