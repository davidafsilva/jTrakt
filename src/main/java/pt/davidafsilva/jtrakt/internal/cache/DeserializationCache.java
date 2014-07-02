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

package pt.davidafsilva.jtrakt.internal.cache;

import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

import java.util.Objects;

/**
 * This is a simple
 *
 * @author David Silva
 */
public enum DeserializationCache {
    INSTANCE; // singleton

    /**
     * Cleans up the cache currently associated with the thread being executed.
     */
    public void clean() {
        LocalCache.INSTANCE.clean();
    }

    /**
     * Caches the given object at the thread currently being executed.
     *
     * @param object
     *         the object to cache
     * @param <T>
     *         the type of the object
     * @throws java.lang.NullPointerException
     *         if {@code object} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public <T> void put(T object) {
        Objects.requireNonNull(object);
        final Class<T> clazz = (Class<T>) object.getClass();
        LocalCache.INSTANCE.put(clazz, object);
    }

    /**
     * <p>
     * Retrieves a season from the cache with the given season number.
     * </p>
     *
     * <p>
     * This is only useful if a single show is being deserialized, otherwise
     * one might get the season for the wrong show.
     * </p>
     *
     * @param seasonNumber
     *         the season number
     * @return a cached season, {@code null} if no season with the given number
     * is found.
     */
    public TvShowSeason getTvShowSeason(final int seasonNumber) {
        return LocalCache.INSTANCE.getCachedObject(TvShowSeason.class,
               season -> season.getNumber() == seasonNumber);
    }
}
