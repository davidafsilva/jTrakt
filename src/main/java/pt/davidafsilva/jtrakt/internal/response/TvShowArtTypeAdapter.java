package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.tv.TvShowArt;

import java.io.IOException;

/**
 * Deserialization entity for {@link TvShowArt} objects.
 *
 * @author David Silva
 */
final class TvShowArtTypeAdapter extends ObjectTypeAdapter<TvShowArt> {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowArtTypeAdapter(Gson gson) {
		super(gson);
	}

	private enum Fields {
		BANNER, FANART, POSTER
	}

	@Override
	TvShowArt createInstance() {
		return new TvShowArt();
	}

	@Override
	void updateFieldValue(TvShowArt object, String fieldName, JsonReader in) throws IOException {
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
				case BANNER:
					object.setBanner(readString(in));
					break;
				case FANART:
					object.setFanArt(readString(in));
					break;
				case POSTER:
					object.setPoster(readString(in));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
