package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.People;
import pt.davidafsilva.jtrakt.model.TvShow;
import pt.davidafsilva.jtrakt.model.TvShowSummary;

import java.io.IOException;
import java.time.ZoneOffset;

/**
 * Deserialization entity for {@link TvShowSummary} objects.
 *
 * @author David Silva
 */
final class TvShowSummaryTypeAdapter extends TvShowTypeAdapter {

	/**
	 * Default constructor for the type adapter
	 *
	 * @param gson
	 * 		the GSON object
	 */
	TvShowSummaryTypeAdapter(Gson gson) {
		super(gson);
	}

	private enum Fields {
		FIRST_AIRED, FIRST_AIRED_ISO, AIR_TIME, AIR_DAY,
		FIRST_AIRED_UTC, AIR_DAY_UTC, AIR_TIME_UTC, LAST_UPDATED, PEOPLE;
	}

	@Override
	TvShow createInstance() {
		return new TvShowSummary();
	}

	@Override
	void updateFieldValue(TvShow object, String fieldName, JsonReader in) throws IOException {
		final String properFieldName = fieldName.toUpperCase();
		Fields field = null;
		try {
			field = Fields.valueOf(properFieldName);
		} catch (IllegalArgumentException e) {
			// fallback
			super.updateFieldValue(object, fieldName, in);
		}

		TvShowSummary show = (TvShowSummary) object;
		if (field != null) {
			switch (field) {
				case FIRST_AIRED:
				case FIRST_AIRED_ISO:
				case AIR_TIME:
				case AIR_DAY:
					// ignore, we'll use UTC values when possible
					in.skipValue();
					break;
				case FIRST_AIRED_UTC:
					show.setFirstAired(readDateTimeTimestamp(in, ZoneOffset.UTC));
					break;
				case AIR_DAY_UTC:
					show.setAirDay(readString(in));
					break;
				case AIR_TIME_UTC:
					show.setAirTime(readString(in));
					break;
				case LAST_UPDATED:
					show.setLastUpdated(readLong(in));
					break;
				case PEOPLE:
					show.setPeople(readObject(in, People.class));
					break;
				default:
					in.skipValue();
					logger.warning(String.format("Unmapped field: %s.", fieldName));
					break;
			}
		}
	}
}
