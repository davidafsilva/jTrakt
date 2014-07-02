/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *          * Redistributions of source code must retain the above copyright
 *              notice, this list of conditions and the following disclaimer.
 *          * Redistributions in binary form must reproduce the above copyright
 *              notice, this list of conditions and the following disclaimer in the
 *              documentation and/or other materials provided with the distribution.
 *          * Neither the name of the <organization> nor the
 *              names of its contributors may be used to endorse or promote products
 *              derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.common.People;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;

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
