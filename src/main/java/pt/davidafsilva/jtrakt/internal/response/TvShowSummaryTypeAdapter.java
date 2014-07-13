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
import pt.davidafsilva.jtrakt.model.common.People;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;

import java.time.ZoneOffset;
import java.util.function.Supplier;

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
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    TvShowSummaryTypeAdapter(final Gson gson, final Supplier<TvShow>
            objectConstructor) {
        super(gson, objectConstructor);
    }

    @Override
    void setupFieldMapping(final FieldMappingBuilder<TvShow> builder) {
        super.setupFieldMapping(builder);
        builder.add("first_aired", DEFAULT_MAPPER);
        builder.add("first_aired_iso", DEFAULT_MAPPER);
        builder.add("airtime", DEFAULT_MAPPER);
        builder.add("airday", DEFAULT_MAPPER);
        builder.add("first_aired_utc", (stream, obj) -> obj.setFirstAired(
                readDateTimeTimestamp(stream, ZoneOffset.UTC)));
        builder.add("air_day_utc",
                    (stream, obj) -> obj.setAirDay(readString(stream)));
        builder.add("air_time_utc",
                    (stream, obj) -> obj.setAirTime(readString(stream)));
        builder.add("last_updated",
                    (stream, obj) -> ((TvShowSummary) obj).setLastUpdated(
                            readLong(stream)));
        builder.add("people", (stream, obj) -> ((TvShowSummary) obj).setPeople(
                readObject(stream, People.class)));
    }
}
