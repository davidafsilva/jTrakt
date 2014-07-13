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
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import pt.davidafsilva.jtrakt.model.common.Actor;
import pt.davidafsilva.jtrakt.model.common.Genre;
import pt.davidafsilva.jtrakt.model.common.People;
import pt.davidafsilva.jtrakt.model.common.Rating;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowArt;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisodeSummary;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * This class defines the creator of the object deserialization entities.
 *
 * @author David Silva
 */
public enum ObjectTypeAdapterFactory implements TypeAdapterFactory {
    INSTANCE; // singleton

    // constructors map
    private final Map<Class<?>, Tuple> constructors = new HashMap<>();

    /**
     * Default constructor
     */
    private ObjectTypeAdapterFactory() {
        // build the constructors map
        constructors.put(Actor.class, Tuple.<AdapterConstructor, Supplier>of(
                ActorTypeAdapter::new, Actor::new));
        constructors.put(TvShow.class, Tuple.<AdapterConstructor, Supplier>of(
                TvShowTypeAdapter::new, TvShow::new));
        constructors.put(TvShowSummary.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowSummaryTypeAdapter::new,
                                 TvShowSummary::new));
        constructors.put(Genre.class, Tuple.<AdapterConstructor, Supplier>of(
                GenreTypeAdapter::new, Genre::new));
        constructors.put(Rating.class, Tuple.<AdapterConstructor, Supplier>of(
                RatingTypeAdapter::new, Rating::new));
        constructors.put(TvShowArt.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowArtTypeAdapter::new, TvShowArt::new));
        constructors.put(People.class, Tuple.<AdapterConstructor, Supplier>of(
                PeopleTypeAdapter::new, People::new));
        constructors.put(TvShowSeason.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowSeasonTypeAdapter::new,
                                 TvShowSeason::new));
        constructors.put(TvShowEpisode.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowEpisodeTypeAdapter::new,
                                 TvShowEpisode::new));
        constructors.put(TvShowSeasonEpisode.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowSeasonEpisodeTypeAdapter::new,
                                 TvShowSeasonEpisode::new));
        constructors.put(TvShowEpisodeSummary.class,
                         Tuple.<AdapterConstructor, Supplier>of(
                                 TvShowEpisodeSummaryTypeAdapter::new,
                                 TvShowEpisodeSummary::new));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        final ObjectTypeAdapter<T> adapter;

        // search for the constructor
        final Tuple<AdapterConstructor, Supplier<?>> constructorTuple =
                constructors.get(type.getRawType());
        if (constructorTuple != null) {
            adapter = (ObjectTypeAdapter<T>) constructorTuple.
                    left.create(gson, constructorTuple.right);
            adapter.initialize();
        } else {
            // we don't support its
            adapter = null;
        }

        return adapter;
    }

    /**
     * Defines the contract of each adapter constructor jn order
     * to abstract the instantiation of a type adapter.
     */
    @FunctionalInterface
    private interface AdapterConstructor<T> {
        ObjectTypeAdapter<T> create(final Gson gson,
                                    final Supplier<T> typeConstructor);
    }

    /**
     * Auxiliary tuple container
     */
    private static final class Tuple<T1, T2> {

        // properties
        private final T1 left;
        private final T2 right;

        /**
         * Default constructor
         *
         * @param left
         *         the left object
         * @param right
         *         the right object
         */
        private Tuple(final T1 left, final T2 right) {
            this.left = left;
            this.right = right;
        }

        private static <T1, T2> Tuple<T1, T2> of(final T1 left,
                                                 final T2 right) {
            return new Tuple<>(left, right);
        }
    }
}
