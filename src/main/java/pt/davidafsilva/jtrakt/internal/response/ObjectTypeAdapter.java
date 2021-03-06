/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the <organization> nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * <p>
 * The base object deserialization entity. It handles most of the
 * deserialization logic of our object model, delegating only the concrete
 * object fields deserialization.
 * </p>
 * <p>
 * Also, it provides a set of much needed functionality to be used by
 * concrete object type adapters, such as auxiliary reader methods.
 * </p>
 *
 * @param <T>
 *         the type of the object being (de)serialized.
 * @author David Silva
 */
abstract class ObjectTypeAdapter<T> extends BaseTypeAdapter<T> {

    // the default field mapper
    protected final FieldMapper<T> DEFAULT_MAPPER =
            (stream, obj) -> stream.skipValue();

    // the field mapping map
    private final Map<String, FieldMapper<T>> fieldMap = new HashMap<>();

    /**
     * Default constructor for the object type adapter
     *
     * @param gson
     *         the GSON object
     * @param objectConstructor
     *         the object constructor
     */
    ObjectTypeAdapter(final Gson gson, final Supplier<T> objectConstructor) {
        super(gson, objectConstructor);
    }

    /**
     * initializes the type adapter
     */
    void initialize() {
        setupFieldMapping(new FieldMappingBuilder<>(fieldMap));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleToken(final JsonReader in, final JsonToken token,
                               final T obj)
            throws IOException {
        int fieldsNumber = 0;
        int errorFieldsDetected = 0;
        switch (token) {
            case BEGIN_OBJECT:
                in.beginObject();
                while (in.hasNext()) {
                    final String fieldName = in.nextName();

                    // detect error responses
                    if ("status".equals(fieldName)) {
                        errorFieldsDetected++;
                    } else if ("error".equals(fieldName)) {
                        errorFieldsDetected++;
                    }

                    fieldMap.getOrDefault(fieldName.toUpperCase(),
                                          DEFAULT_MAPPER).map(in, obj);
                    fieldsNumber++;
                }
                in.endObject();
                break;
            default:
                throw new IllegalStateException(
                        String.format("Invalid JSON token received: %s",
                                      token.name()));
        }

        // check if we detected an error
        if (errorFieldsDetected == fieldsNumber) {
            logger.warning("error detected!");
            throw new NoResultError();
        }
    }

    // abstract methods

    /**
     * Setups the field mapping for this type adapter
     *
     * @param builder
     *         the field mapping builder
     */
    abstract void setupFieldMapping(final FieldMappingBuilder<T> builder);

    // auxiliary methods

    /**
     * Reads a {@link String} from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @return the read String
     * @throws IOException
     *         if an error occurs while reading the object from the stream
     */
    String readString(final JsonReader reader) throws IOException {
        return readObject(reader, String.class);
    }

    /**
     * Reads an {@link Integer} from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @return the read Integer
     * @throws IOException
     *         if an error occurs while reading the object from the stream
     */
    Integer readInt(final JsonReader reader) throws IOException {
        return readObject(reader, Integer.class);
    }

    /**
     * Reads a {@link Boolean} from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @return the read Boolean
     * @throws IOException
     *         if an error occurs while reading the object from the stream
     */
    Boolean readBoolean(final JsonReader reader) throws IOException {
        return readObject(reader, Boolean.class);
    }

    /**
     * Reads a {@link Long} from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @return the read Long
     * @throws IOException
     *         if an error occurs while reading the object from the stream
     */
    Long readLong(final JsonReader reader) throws IOException {
        return readObject(reader, Long.class);
    }

    /**
     * Reads a {@link LocalDateTime} from the given JSON stream with the
     * given {@link ZoneOffset offset}.
     *
     * @param reader
     *         the JSON stream
     * @param offset
     *         the zone offset of the date
     * @return the read date time
     * @throws IOException
     *         if an error occurs while reading the object from the stream
     */
    LocalDateTime readDateTimeTimestamp(final JsonReader reader,
                                        final ZoneOffset offset)
            throws IOException {
        final Long timestamp = readLong(reader);
        return timestamp == null ?
               null :
               LocalDateTime.ofEpochSecond(timestamp, 0, offset);
    }

    /**
     * Reads a {@link Set} of {@code C} objects from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @param clazz
     *         the generic class type of the collection
     * @param <C>
     *         the type of objects to be read by the stream (stored in the
     *         collection)
     * @return the read set
     * @throws IOException
     *         if an error occurs while reading the collection from the stream
     */
    <C> Set<C> readSet(final JsonReader reader, final Class<C> clazz)
            throws IOException {
        Set<C> set = new HashSet<>();
        readCollection(reader, clazz, set);
        return set;
    }

    /**
     * Reads a {@link List} of {@code C} objects from the given JSON stream
     *
     * @param reader
     *         the JSON stream
     * @param clazz
     *         the generic class type of the collection
     * @param <C>
     *         the type of objects to be read by the stream (stored in the
     *         collection)
     * @return the read list
     * @throws IOException
     *         if an error occurs while reading the collection from the stream
     */
    <C> List<C> readList(final JsonReader reader, final Class<C> clazz)
            throws IOException {
        List<C> list = new ArrayList<>();
        readCollection(reader, clazz, list);
        return list;
    }

    /**
     * Reads the objects associated with a {@link Collection} from the given
     * JSON stream
     *
     * @param reader
     *         the JSON stream
     * @param clazz
     *         the generic class type of the collection
     * @param <C>
     *         the type of objects to be read by the stream (stored in the
     *         collection)
     * @param destiny
     *         the collection to store the read objects
     * @throws IOException
     *         if an error occurs while reading the collection from the stream
     */
    <C> void readCollection(final JsonReader reader, final Class<C> clazz,
                            final Collection<C> destiny)
            throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            destiny.add(gson.getAdapter(clazz).read(reader));
        }
        reader.endArray();
    }

    /**
     * The field mapping builder
     *
     * @param <T>
     *         the type of the object for the mapping
     */
    static final class FieldMappingBuilder<T> {

        // properties
        private final Map<String, FieldMapper<T>> container;

        /**
         * Default constructor
         *
         * @param container
         *         the mapping container
         */
        private FieldMappingBuilder(final Map<String,
                FieldMapper<T>> container) {
            this.container = container;
        }

        /**
         * Adds the mapping for the specified field
         *
         * @param fieldName
         *         the field name
         * @param mapper
         *         the field mapping
         * @return the builder itself
         * @throws NullPointerException
         *         if one of {@code fieldName} or {@code mapper} is {@code
         *         null}
         */
        public FieldMappingBuilder<T> add(final String fieldName,
                                          final FieldMapper<T> mapper) {
            Objects.requireNonNull(fieldName);
            Objects.requireNonNull(mapper);
            container.put(fieldName.toUpperCase(), mapper);
            return this;
        }
    }
}
