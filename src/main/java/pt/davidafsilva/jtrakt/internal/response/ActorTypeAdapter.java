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
import pt.davidafsilva.jtrakt.model.common.Actor;

import java.util.function.Supplier;

/**
 * Deserialization entity for {@link Actor} objects.
 *
 * @author David Silva
 */
final class ActorTypeAdapter extends ObjectTypeAdapter<Actor> {

    /**
     * Default constructor for the type adapter
     *
     * @param gson
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    ActorTypeAdapter(final Gson gson, final Supplier<Actor> objectConstructor) {
        super(gson, objectConstructor);
    }

    @Override
    void setupFieldMapping(final FieldMappingBuilder<Actor> builder) {
        builder.add("name", (stream, obj) -> obj.setName(readString(stream)));
        builder.add("character",
                    (stream, obj) -> obj.setCharacter(readString(stream)));
        builder.add("images", (stream, obj) -> {
            //TODO: might change this if more image types are actually supported
            switch (stream.peek()) {
                case BEGIN_OBJECT:
                    stream.beginObject();
                    final JsonToken peek = stream.peek();
                    if (peek == JsonToken.NAME &&
                        "headshot".equals(stream.nextName())) {
                        obj.setPictureUrl(readString(stream));
                    } else {
                        stream.skipValue();
                    }
                    stream.endObject();
                    break;
                default:
                    break;
            }
        });
    }
}
