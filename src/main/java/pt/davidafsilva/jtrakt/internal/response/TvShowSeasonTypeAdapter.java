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
import com.google.gson.stream.JsonToken;
import pt.davidafsilva.jtrakt.model.tv.TvShowArt;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

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
