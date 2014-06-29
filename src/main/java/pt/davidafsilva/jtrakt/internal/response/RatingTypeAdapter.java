package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.Rating;

import java.io.IOException;

/**
 * Deserialization entity for {@link Rating} objects.
 *
 * @author David Silva
 */
final class RatingTypeAdapter extends ObjectTypeAdapter<Rating> {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	RatingTypeAdapter(Gson gson) {
		super(gson);
	}

	private enum Fields {
		PERCENTAGE, VOTES, LOVED, HATED
	}

	@Override
	Rating createInstance() {
		return new Rating();
	}

	@Override
	void updateFieldValue(Rating object, String fieldName, JsonReader in) throws IOException {
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
				case PERCENTAGE:
					object.setPercentage(readInt(in));
					break;
				case VOTES:
					object.setVotes(readInt(in));
					break;
				case LOVED:
					object.setLoved(readInt(in));
					break;
				case HATED:
					object.setHated(readInt(in));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}

}
