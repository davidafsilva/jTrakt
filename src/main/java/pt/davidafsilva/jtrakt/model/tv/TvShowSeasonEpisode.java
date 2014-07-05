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

package pt.davidafsilva.jtrakt.model.tv;

import pt.davidafsilva.jtrakt.model.common.Rating;

import java.time.LocalDateTime;

/**
 * <p>This class represents a TV show episode with richer information (compared
 * to {@link pt.davidafsilva.jtrakt.model.tv.TvShowEpisode}).</p>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "season": 1,
 *   "episode": 1,
 *   "number": 1,
 *   "tvdb_id": 4765079,
 *   "title": "Minimum Viable Product",
 *   "overview": "Attending an elaborate launch party, Richard and his computer
 *                programmer friends - Big Head, Dinesh and Gilfoyle - dream of
 *                making it big. Instead, they're living in the communal Hacker
 *                Hostel owned by former programmer Erlich, who gets to claim
 *                ten percent of anything they invent there. When it becomes
 *                clear that Richard has developed a powerful compression by
 *                buyout by his firm, Hooli. But Richard holds back when
 *                well-known investor Peter Gregory makes a counteroffer. ",
 *   "first_aired": 1396846800,
 *   "first_aired_iso": "2014-04-06T22:00:00-04:00",
 *   "first_aired_utc": 1396836000,
 *   "url": "http://trakt.tv/show/silicon-valley/season/1/episode/1",
 *   "screen": "http://slurm.trakt.us/images/episodes/23332-1-1.16.jpg",
 *   "images": {
 *     "screen": "http://slurm.trakt.us/images/episodes/23332-1-1.16.jpg"
 *   },
 *   "ratings": {
 *     "percentage": 79,
 *     "votes": 1216,
 *     "loved": 1167,
 *     "hated": 49
 *   }
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class TvShowSeasonEpisode extends TvShowEpisode {

    // properties
    private int tvdbId;
    private String title;
    private String overview;
    private LocalDateTime firstAired;
    private String url;
    private String screenUrl;
    private Rating rating;

    /**
     * Returns the TVDB identifier
     *
     * @return the TVDB identifier
     */
    public int getTvdbId() {
        return tvdbId;
    }

    /**
     * Sets the TVDB identifier
     *
     * @param tvdbId
     *         the TVDB identifier
     */
    public void setTvdbId(final int tvdbId) {
        this.tvdbId = tvdbId;
    }

    /**
     * Returns the title of this episode
     *
     * @return the episode's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this episode
     *
     * @param title
     *         the episode's title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the overview description of this episode
     *
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the overview description of this episode
     *
     * @param overview
     *         the overview
     */
    public void setOverview(final String overview) {
        this.overview = overview;
    }

    /**
     * Returns the date time of this episode's first air date.
     *
     * @return the first air date of the episode
     */
    public LocalDateTime getFirstAired() {
        return firstAired;
    }

    /**
     * Sets the first air date of this episode
     *
     * @param firstAired
     *         the first aired date time
     */
    public void setFirstAired(final LocalDateTime firstAired) {
        this.firstAired = firstAired;
    }

    /**
     * Returns the Trakt web URL for this episode
     *
     * @return the web URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the Trakt web URL for this episode
     *
     * @param url
     *         the web URL
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns the screen image URL for this episode
     *
     * @return the screen image URL
     */
    public String getScreenUrl() {
        return screenUrl;
    }

    /**
     * Sets the screen image URL for this episode
     *
     * @param screenUrl
     *         the episode's screen image URL
     */
    public void setScreenUrl(final String screenUrl) {
        this.screenUrl = screenUrl;
    }

    /**
     * Returns the episode rating
     *
     * @return the episode's rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the episode rating
     *
     * @param rating
     *         the episode's rating
     */
    public void setRating(final Rating rating) {
        this.rating = rating;
    }
}
