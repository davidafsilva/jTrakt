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
import pt.davidafsilva.jtrakt.model.common.Genre;
import pt.davidafsilva.jtrakt.model.common.Rating;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowArt;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

import java.time.ZoneOffset;
import java.util.function.Supplier;

/**
 * Deserialization entity for {@link TvShow} objects.
 *
 * @author David Silva
 */
class TvShowTypeAdapter extends ObjectTypeAdapter<TvShow> {

    static final ZoneOffset PDT_OFFSET = ZoneOffset.ofHours(-7);

    /**
     * Default constructor for the type adapter
     *
     * @param gson
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    TvShowTypeAdapter(final Gson gson, final Supplier<TvShow>
            objectConstructor) {
        super(gson, objectConstructor);
    }

    @Override
    void setupFieldMapping(final FieldMappingBuilder<TvShow> builder) {
        builder.add("title", (stream, obj) -> obj.setTitle(readString(stream)));
        builder.add("year", (stream, obj) -> obj.setYear(readInt(stream)));
        builder.add("url", (stream, obj) -> obj.setUrl(readString(stream)));
        builder.add("first_aired", (stream, obj) -> obj.setFirstAired(
                readDateTimeTimestamp(stream, PDT_OFFSET)));
        builder.add("country",
                    (stream, obj) -> obj.setCountry(readString(stream)));
        builder.add("overview",
                    (stream, obj) -> obj.setOverview(readString(stream)));
        builder.add("runtime",
                    (stream, obj) -> obj.setRuntime(readInt(stream)));
        builder.add("network",
                    (stream, obj) -> obj.setNetwork(readString(stream)));
        builder.add("air_day",
                    (stream, obj) -> obj.setAirDay(readString(stream)));
        builder.add("air_time",
                    (stream, obj) -> obj.setAirTime(readString(stream)));
        builder.add("certification",
                    (stream, obj) -> obj.setCertification(readString(stream)));
        builder.add("imdb_id",
                    (stream, obj) -> obj.setImdbId(readString(stream)));
        builder.add("tvdb_id", (stream, obj) -> obj.setTvdbId(readInt(stream)));
        builder.add("tvrage_id",
                    (stream, obj) -> obj.setTvrageId(readInt(stream)));
        builder.add("ended",
                    (stream, obj) -> obj.setEnded(readBoolean(stream)));
        builder.add("images", (stream, obj) -> obj.setImages(
                readObject(stream, TvShowArt.class)));
        builder.add("ratings", (stream, obj) -> obj.setRating(
                readObject(stream, Rating.class)));
        builder.add("genres", (stream, obj) -> obj.setGenres(
                readSet(stream, Genre.class)));
        builder.add("seasons", (stream, obj) -> obj.setSeasons(
                readList(stream, TvShowSeason.class)));
    }
}
