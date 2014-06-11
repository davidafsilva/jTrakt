package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.Genre;
import pt.davidafsilva.jtrakt.model.Rating;
import pt.davidafsilva.jtrakt.model.TvShow;
import pt.davidafsilva.jtrakt.model.TvShowArt;
import pt.davidafsilva.jtrakt.model.TvShowSeason;

import java.io.IOException;
import java.time.ZoneOffset;

/**
 * The {@link pt.davidafsilva.jtrakt.model.TvShow} type adapter for
 * serialization and deserialization purposes.
 *
 * <p>
 * An example of a correct JSON serialized {@link pt.davidafsilva.jtrakt.model.TvShow}
 * would be:
 * <pre>
 * {
 *  "title": "Silicon Valley",
 *  "year": 2014,
 *  "url": "http://trakt.tv/show/silicon-valley",
 *  "first_aired": 1396767600,
 *  "country": "United States",
 *  "overview": "In the high-tech gold rush of modern Silicon Valley, the people most qualified to succeed are the
 *              least capable of handling success. \n\nA comedy partially inspired by Mike Judge's own experiences
 *              as a Silicon Valley engineer in the late 1980s.",
 *  "runtime": 30,
 *  "network": "HBO",
 *  "air_day": "Sunday",
 *  "air_time": "10:00pm",
 *  "certification": "TV-14",
 *  "imdb_id": "tt2575988",
 *  "tvdb_id": 277165,
 *  "tvrage_id": 33759,
 *  "ended": false,
 *  "images": {
 *      "poster": "http://slurm.trakt.us/images/posters/23332.10.jpg",
 *      "fanart": "http://slurm.trakt.us/images/fanart/23332.10.jpg",
 *      "banner": "http://slurm.trakt.us/images/banners/23332.10.jpg"
 *  },
 *  "ratings": {
 *      "percentage": 82,
 *      "votes": 258,
 *      "loved": 241,
 *      "hated": 17
 *  },
 *  "genres": [
 *      "Comedy"
 *  ]
 * }
 * </pre>
 *
 * @author David Silva
 */
public class TvShowTypeAdapter extends AbstractObjectTypeAdapter<TvShow> {

	static final ZoneOffset PDT_OFFSET = ZoneOffset.ofHours(-7);

	/**
	 * Default constructor for the type dater
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowTypeAdapter(Gson gson) {
		super(gson);
	}

	private enum Fields {
		TITLE, YEAR, URL, FIRST_AIRED, COUNTRY, OVERVIEW, RUNTIME, NETWORK,
		AIR_DAY, AIR_TIME, CERTIFICATION, IMDB_ID, TVDB_ID, TVRAGE_ID,
		ENDED, IMAGES, RATINGS, GENRES, SEASONS
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	TvShow createInstance() {
		return new TvShow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void updateFieldValue(final TvShow object, final String fieldName, final JsonReader in) throws IOException {
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
				case TITLE:
					object.setTitle(readString(in));
					break;
				case YEAR:
					object.setYear(readInt(in));
					break;
				case URL:
					object.setUrl(readString(in));
					break;
				case FIRST_AIRED:
					object.setFirstAired(readDateTimeTimestamp(in, PDT_OFFSET));
					break;
				case COUNTRY:
					object.setCountry(readString(in));
					break;
				case OVERVIEW:
					object.setOverview(readString(in));
					break;
				case RUNTIME:
					object.setRuntime(readInt(in));
					break;
				case NETWORK:
					object.setNetwork(readString(in));
					break;
				case AIR_DAY:
					object.setAirDay(readString(in));
					break;
				case AIR_TIME:
					object.setAirTime(readString(in));
					break;
				case CERTIFICATION:
					object.setCertification(readString(in));
					break;
				case IMDB_ID:
					object.setImdbId(readString(in));
					break;
				case TVDB_ID:
					object.setTvdbId(readInt(in));
					break;
				case TVRAGE_ID:
					object.setTvrageId(readInt(in));
					break;
				case ENDED:
					object.setEnded(readBoolean(in));
					break;
				case IMAGES:
					object.setImages(readObject(in, TvShowArt.class));
					break;
				case RATINGS:
					object.setRatings(readObject(in, Rating.class));
					break;
				case GENRES:
					object.setGenres(readSet(in, Genre.class));
					break;
				case SEASONS:
					object.setSeasons(readList(in, TvShowSeason.class));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
