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
import com.google.gson.stream.JsonToken;
import pt.davidafsilva.jtrakt.model.tv.TvShowArt;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

import java.util.function.Supplier;

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
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    TvShowSeasonTypeAdapter(final Gson gson, final Supplier<TvShowSeason>
            objectConstructor) {
        super(gson, objectConstructor);
    }

    @Override
    void setupFieldMapping(final FieldMappingBuilder<TvShowSeason> builder) {
        builder.add("season", (stream, obj) -> obj.setNumber(readInt(stream)));
        builder.add("url", (stream, obj) -> obj.setUrl(readString(stream)));
        builder.add("episodes", (stream, obj) -> {
            if (stream.peek() == JsonToken.NUMBER) {
                obj.setEpisodesNumber(readInt(stream));
            } else {
                obj.setEpisodes(readList(stream, TvShowEpisode.class));
                obj.getEpisodes().forEach(episode -> episode.setSeason(obj));
                obj.setEpisodesNumber(obj.getEpisodes().size());
            }
        });
        builder.add("images", (stream, obj) -> obj.setImages(
                readObject(stream, TvShowArt.class)));
    }
}
