package pt.davidafsilva.jtrakt.model.tv;

/**
 * This class represents a TV show episode summary.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "show": {
 *     "title": "Silicon Valley",
 *     "year": 2014,
 *     "url": "http://trakt.tv/show/silicon-valley",
 *     "first_aired": 1396846800,
 *     "first_aired_iso": "2014-04-06T22:00:00-04:00",
 *     "first_aired_utc": 1396836000,
 *     "country": "United States",
 *     "overview": "In the high-tech gold rush of modern Silicon Valley, the
 * people
 *                  most qualified to succeed are the least capable of handling
 *                  success. \n\nA comedy partially inspired by Mike Judge's
 * own
 *                  experiences as a Silicon Valley engineer in the late
 * 1980s.",
 *     "runtime": 30,
 *     "network": "HBO",
 *     "air_day": "Sunday",
 *     "air_time": "10:00pm",
 *     "certification": "TV-14",
 *     "imdb_id": "tt2575988",
 *     "tvdb_id": 277165,
 *     "tvrage_id": 33759,
 *     "images": {
 *       "poster": "http://slurm.trakt.us/images/posters/23332.16.jpg",
 *       "fanart": "http://slurm.trakt.us/images/fanart/23332.16.jpg",
 *       "banner": "http://slurm.trakt.us/images/banners/23332.16.jpg"
 *     },
 *     "ratings": {
 *       "percentage": 82,
 *       "votes": 930,
 *       "loved": 888,
 *       "hated": 42
 *     },
 *     "genres": [
 *       "Comedy"
 *     ]
 *   },
 *   "episode": {
 *     "season": 1,
 *     "number": 1,
 *     "tvdb_id": 4765079,
 *     "imdb_id": "tt3222784",
 *     "title": "Minimum Viable Product",
 *     "overview": "Attending an elaborate launch party, Richard and his
 * computer
 *                  programmer friends - Big Head, Dinesh and Gilfoyle - dream
 *                  of making it big. Instead, they're living in the communal
 *                  Hacker Hostel owned by former programmer Erlich, who gets
 *                  to claim ten percent of anything they invent there. When it
 *                  becomes clear that Richard has developed a powerful
 * compression
 *                  algorithm for his website, Pied Piper, he finds himself
 * courted
 *                  by Gavin Belson, his egomaniacal corporate boss, who offers
 *                  a $10 million buyout by his firm, Hooli. But Richard holds
 *                  back when well-known investor Peter Gregory makes a
 *                  counteroffer. ",
 *     "url": "http://trakt.tv/show/silicon-valley/season/1/episode/1",
 *     "first_aired": 1396846800,
 *     "first_aired_iso": "2014-04-06T22:00:00-04:00",
 *     "first_aired_utc": 1396836000,
 *     "images": {
 *       "screen": "http://slurm.trakt.us/images/episodes/23332-1-1.16.jpg"
 *     },
 *     "ratings": {
 *       "percentage": 79,
 *       "votes": 1212,
 *       "loved": 1164,
 *       "hated": 48
 *     }
 *   }
 * }
 *
 * </pre>
 *
 * @author David Silva
 */
public final class TvShowEpisodeSummary {

    // properties
    private TvShow show;
    private TvShowSeasonEpisode episode;

    /**
     * Returns the TV show of this summary
     *
     * @return the TV show
     */
    public TvShow getShow() {
        return show;
    }

    /**
     * Sets the TV show of this summary
     *
     * @param show
     *         the TV show
     */
    public void setShow(final TvShow show) {
        this.show = show;
    }

    /**
     * Returns the actual episode summary
     *
     * @return the episode summary
     */
    public TvShowSeasonEpisode getEpisode() {
        return episode;
    }

    /**
     * Sets the episode summary
     *
     * @param episode
     *         the episode summary
     */
    public void setEpisode(final TvShowSeasonEpisode episode) {
        this.episode = episode;
    }
}
