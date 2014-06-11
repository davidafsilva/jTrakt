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
public class TvShowSeasonTypeAdapter extends AbstractObjectTypeAdapter<TvShowSeason> {

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowSeasonTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		SEASON, EPISODES
	}

	@Override
	TvShowSeason createInstance() {
		return new TvShowSeason();
	}

	@Override
	void updateFieldValue(final TvShowSeason object, final String fieldName, final JsonReader in) throws IOException {
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
					object.setNumber(readInt(in));
					break;
				case EPISODES:
					object.setEpisodes(readList(in, TvShowEpisode.class));
					object.getEpisodes().forEach(episode -> episode.setSeason(object));
					object.setEpisodesNumber(object.getEpisodes().size());
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
