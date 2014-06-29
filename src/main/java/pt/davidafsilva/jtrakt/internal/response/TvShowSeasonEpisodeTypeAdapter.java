package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.internal.cache.DeserializationCache;
import pt.davidafsilva.jtrakt.model.common.Rating;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

import java.io.IOException;
import java.time.ZoneOffset;

/**
 * Deserialization entity for {@link TvShowSeasonEpisode} objects.
 *
 * @author David Silva
 */
final class TvShowSeasonEpisodeTypeAdapter extends TvShowEpisodeTypeAdapter {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowSeasonEpisodeTypeAdapter(final Gson gson) {
		super(gson);
	}

	private enum Fields {
		TVDB_ID, TITLE, OVERVIEW, URL, SCREEN, RATINGS,
		FIRST_AIRED, FIRST_AIRED_ISO, FIRST_AIRED_UTC
	}

	@Override
    TvShowSeasonEpisode createInstance() {
		return new TvShowSeasonEpisode();
	}

	@Override
	void updateFieldValue(final TvShowEpisode object, final String fieldName, final JsonReader in)
			throws IOException {
		final String properFieldName = fieldName.toUpperCase();
		Fields field = null;
		try {
			field = Fields.valueOf(properFieldName);
		} catch (IllegalArgumentException e) {
			// fallback
			super.updateFieldValue(object, fieldName, in);
		}

		TvShowSeasonEpisode episodeSummary = (TvShowSeasonEpisode) object;
		if (field != null) {
			switch (field) {
				case FIRST_AIRED:
				case FIRST_AIRED_ISO:
					// ignore, we'll use UTC values when possible
					in.skipValue();
					break;
				case FIRST_AIRED_UTC:
					episodeSummary.setFirstAired(readDateTimeTimestamp(in, ZoneOffset.UTC));
					break;
				case TVDB_ID:
					episodeSummary.setTvdbId(readInt(in));
					break;
				case TITLE:
					episodeSummary.setTitle(readString(in));
					break;
				case OVERVIEW:
					episodeSummary.setOverview(readString(in));
					break;
				case URL:
					episodeSummary.setUrl(readString(in));
					break;
				case SCREEN:
					episodeSummary.setScreenUrl(readString(in));
					break;
				case RATINGS:
					episodeSummary.setRating(readObject(in, Rating.class));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}

	@Override
	void onReadFinished(final TvShowEpisode object) {
        TvShowSeason season = DeserializationCache.INSTANCE.getTvShowSeason(object.getSeason().getNumber());
        if (season == null) {
            DeserializationCache.INSTANCE.put(season = object.getSeason());
        } else {
            object.setSeason(season);
        }

        season.getEpisodes().add(object);
        season.setEpisodesNumber(season.getEpisodesNumber() + 1);
	}
}
