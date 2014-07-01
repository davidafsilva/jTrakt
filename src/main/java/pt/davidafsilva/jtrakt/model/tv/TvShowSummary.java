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
