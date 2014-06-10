package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.Actor;
import pt.davidafsilva.jtrakt.model.People;

import java.io.IOException;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class PeopleTypeAdapter extends AbstractObjectTypeAdapter<People> {

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	PeopleTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		ACTORS;
	}

	@Override
	People createInstance() {
		return new People();
	}

	@Override
	void updateFieldValue(final People object, final String fieldName, final JsonReader in) throws IOException {
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
				case ACTORS:
					object.setActors(readSet(in, Actor.class));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}