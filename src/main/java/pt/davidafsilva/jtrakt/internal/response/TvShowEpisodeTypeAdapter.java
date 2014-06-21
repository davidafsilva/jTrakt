package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.TvShowSeason;

import java.io.IOException;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowEpisodeTypeAdapter extends AbstractObjectTypeAdapter<TvShowEpisode> {

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowEpisodeTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		SEASON, NUMBER
	}

	@Override
	TvShowEpisode createInstance() {
		return new TvShowEpisode();
	}

	@Override
	void updateFieldValue(final TvShowEpisode object, final String fieldName, final JsonReader in) throws IOException {
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
				case SEASON:
					// will be overridden by seasons adapter, if it's the source
                    final TvShowSeason season = new TvShowSeason();
					season.setNumber(readInt(in));
					object.setSeason(season);
					break;
				case NUMBER:
					object.setNumber(readInt(in));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
