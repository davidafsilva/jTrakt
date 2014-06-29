package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import pt.davidafsilva.jtrakt.model.TvShowArt;
import pt.davidafsilva.jtrakt.model.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.TvShowSeason;

import java.io.IOException;

/**
 * Deserialization entity for {@link TvShowSeason} objects.
 *
 * @author David Silva
 */
final class TvShowSeasonTypeAdapter extends ObjectTypeAdapter<TvShowSeason> {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowSeasonTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		SEASON, EPISODES, URL, IMAGES
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
				case URL:
					object.setUrl(readString(in));
					break;
				case EPISODES:
					if (in.peek() == JsonToken.NUMBER) {
						object.setEpisodesNumber(readInt(in));
					} else {
						object.setEpisodes(readList(in, TvShowEpisode.class));
						object.getEpisodes().forEach(episode -> episode.setSeason(object));
						object.setEpisodesNumber(object.getEpisodes().size());
					}
					break;
				case IMAGES:
					object.setImages(readObject(in, TvShowArt.class));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
