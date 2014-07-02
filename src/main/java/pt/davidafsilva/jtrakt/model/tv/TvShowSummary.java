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

package pt.davidafsilva.jtrakt.model.tv;

import pt.davidafsilva.jtrakt.model.common.People;

/**
 * This class represents a TV show summary.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "title": "Silicon Valley",
 *   "year": 2014,
 *   "url": "http://trakt.tv/show/silicon-valley",
 *   "first_aired": 1396846800,
 *   "first_aired_iso": "2014-04-06T22:00:00-04:00",
 *   "first_aired_utc": 1396836000,
 *   "country": "United States",
 *   "overview": "In the high-tech gold rush of modern Silicon Valley, the people
 *                most qualified to succeed are the least capable of handling
 *                success. \n\nA comedy partially inspired by Mike Judge's own
 *                experiences as a Silicon Valley engineer in the late 1980s.",
 *   "runtime": 30,
 *   "status": "Continuing",
 *   "network": "HBO",
 *   "air_day": "Sunday",
 *   "air_day_utc": "Sunday",
 *   "air_time": "10:00pm",
 *   "air_time_utc": "7:00pm",
 *   "certification": "TV-14",
 *   "imdb_id": "tt2575988",
 *   "tvdb_id": 277165,
 *   "tvrage_id": 33759,
 *   "last_updated": 1402920958,
 *   "poster": "http://slurm.trakt.us/images/posters/23332.16.jpg",
 *   "images": {
 *     "poster": "http://slurm.trakt.us/images/posters/23332.16.jpg",
 *     "fanart": "http://slurm.trakt.us/images/fanart/23332.16.jpg",
 *     "banner": "http://slurm.trakt.us/images/banners/23332.16.jpg"
 *   },
 *   "ratings": {
 *     "percentage": 82,
 *     "votes": 939,
 *     "loved": 897,
 *     "hated": 42
 *   },
 *   "people": {
 *     "actors": [
 *       {
 *         "name": "Thomas Middleditch",
 *         "character": "Richard",
 *         "images": {
 *           "headshot": "http://slurm.trakt.us/images/avatar-large.jpg"
 *         }
 *       }
 *       ...
 *     ]
 *   },
 *   "genres": [
 *     "Comedy"
 *   ]
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class TvShowSummary extends TvShow {

    // properties
    private long lastUpdated;
    private People people;

    /**
     * Returns the timestamp from the last update of this TV show
     *
     * @return the last update timestamp
     */
    public long getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the timestamp for the last update of this TV show
     *
     * @param lastUpdated
     *         the timestamp
     */
    public void setLastUpdated(final long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Returns the people directly involved to this TV show
     *
     * @return the people of this TV show
     */
    public People getPeople() {
        return people;
    }

    /**
     * Sets the people directly involved with this TV show
     *
     * @param people
     *         the people
     */
    public void setPeople(final People people) {
        this.people = people;
    }
}
