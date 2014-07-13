/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *          * Redistributions of source code must retain the above copyright
 *              notice, this list of conditions and the following disclaimer.
 *          * Redistributions in binary form must reproduce the above copyright
 *              notice, this list of conditions and the following disclaimer
 *              in the
 *              documentation and/or other materials provided with the
 *              distribution.
 *          * Neither the name of the <organization> nor the
 *              names of its contributors may be used to endorse or promote
 *              products
 *              derived from this software without specific prior written
 *              permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND
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
import pt.davidafsilva.jtrakt.internal.cache.DeserializationCache;
import pt.davidafsilva.jtrakt.model.common.Rating;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;

import java.time.ZoneOffset;
import java.util.function.Supplier;

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
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    TvShowSeasonEpisodeTypeAdapter(final Gson gson,
                                   final Supplier<TvShowEpisode>
                                           objectConstructor) {
        super(gson, objectConstructor);
    }

    @Override
    void setupFieldMapping(final FieldMappingBuilder<TvShowEpisode> builder) {
        super.setupFieldMapping(builder);
        builder.add("first_aired", DEFAULT_MAPPER);
        builder.add("first_aired_iso", DEFAULT_MAPPER);
        builder.add("first_aired_utc",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setFirstAired(
                            readDateTimeTimestamp(stream, ZoneOffset.UTC)));
        builder.add("tvdb_id",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setTvdbId(
                            readInt(stream)));
        builder.add("title",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setTitle(
                            readString(stream)));
        builder.add("overview",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setOverview(
                            readString(stream)));
        builder.add("url", (stream, obj) -> ((TvShowSeasonEpisode) obj).setUrl(
                readString(stream)));
        builder.add("screen",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setScreenUrl(
                            readString(stream)));
        builder.add("ratings",
                    (stream, obj) -> ((TvShowSeasonEpisode) obj).setRating(
                            readObject(stream, Rating.class)));
    }

    @Override
    void onReadFinished(final TvShowEpisode object) {
        TvShowSeason season = DeserializationCache.INSTANCE.getTvShowSeason(
                object.getSeason().getNumber());
        if (season == null) {
            DeserializationCache.INSTANCE.put(season = object.getSeason());
        } else {
            object.setSeason(season);
        }

        season.getEpisodes().add(object);
        season.setEpisodesNumber(season.getEpisodesNumber() + 1);
    }
}
