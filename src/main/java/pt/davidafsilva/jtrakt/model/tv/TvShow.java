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

import pt.davidafsilva.jtrakt.model.common.BaseModel;
import pt.davidafsilva.jtrakt.model.common.Genre;
import pt.davidafsilva.jtrakt.model.common.Rating;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a TV show.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "title": "Silicon Valley",
 *   "year": 2014,
 *   "url": "http://trakt.tv/show/silicon-valley",
 *   "first_aired": 1396767600,
 *   "country": "United States",
 *   "overview": "In the high-tech gold rush of modern Silicon Valley, the
 * people
 *                most qualified to succeed are the least capable of handling
 *                success. \n\nA comedy partially inspired by Mike Judge's own
 *                experiences as a Silicon Valley engineer in the late 1980s.",
 *   "runtime": 30,
 *   "network": "HBO",
 *   "air_day": "Sunday",
 *   "air_time": "10:00pm",
 *   "certification": "TV-14",
 *   "imdb_id": "tt2575988",
 *   "tvdb_id": 277165,
 *   "tvrage_id": 33759,
 *   "ended": false,
 *   "images": {
 *     "poster": "http://slurm.trakt.us/images/posters/23332.16.jpg",
 *     "fanart": "http://slurm.trakt.us/images/fanart/23332.16.jpg",
 *     "banner": "http://slurm.trakt.us/images/banners/23332.16.jpg"
 *   },
 *   "ratings": {
 *     "percentage": 82,
 *     "votes": 919,
 *     "loved": 877,
 *     "hated": 42
 *   },
 *   "genres": [
 *     "Comedy"
 *   ]
 * }
 * </pre>
 *
 * @author David Silva
 */
public class TvShow extends BaseModel {

    // properties
    private String title;
    private int year;
    private String url;
    private LocalDateTime firstAired;
    private String country;
    private String overview;
    private int runtime;
    private String network;
    private String airDay;
    private String airTime;
    private String certification;
    private String imdbId;
    private int tvdbId;
    private int tvrageId;
    private boolean ended;
    private TvShowArt images;
    private Rating ratings;
    private Set<Genre> genres = new HashSet<>();
    private List<TvShowSeason> seasons = new ArrayList<>();


    /**
     * Returns the TV show title
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the TV show title
     *
     * @param title
     *         the title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the year when the TV show first aired
     *
     * @return the year that the TV show was born
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year when the TV show first aired
     *
     * @param year
     *         the year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Returns the TV show URL
     *
     * @return the TV show URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the TV show
     *
     * @param url
     *         the TV show URL
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns the date time of when the TV show first aired
     *
     * @return the first air date time
     */
    public LocalDateTime getFirstAired() {
        return firstAired;
    }

    /**
     * Sets the first air date time of the TV show
     *
     * @param firstAired
     *         the date time
     */
    public void setFirstAired(final LocalDateTime firstAired) {
        this.firstAired = firstAired;
    }

    /**
     * Returns the country origin of the TV show
     *
     * @return the TV show country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the TV show origin country
     *
     * @param country
     *         the country name
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Returns the TV show overview description
     *
     * @return the TV show overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Sets the TV show overview description
     *
     * @param overview
     *         the overview of the TV show
     */
    public void setOverview(final String overview) {
        this.overview = overview;
    }

    /**
     * Returns the total runtime of the show per episode in minutes
     *
     * @return the TV show's episode runtime in minutes
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Sets the total runtime of the show per episode in minutes
     *
     * @param runtime
     *         the episode runtime in minutes
     */
    public void setRuntime(final int runtime) {
        this.runtime = runtime;
    }

    /**
     * Returns the TV show network
     *
     * @return the TV show network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Sets the network of the TV show
     *
     * @param network
     *         the network
     */
    public void setNetwork(final String network) {
        this.network = network;
    }

    /**
     * Returns the day that typically the TV show airs
     *
     * @return the TV show's air day
     */
    public String getAirDay() {
        return airDay;
    }

    /**
     * Sets the TV show air day
     *
     * @param airDay
     *         the TV show air day
     */
    public void setAirDay(final String airDay) {
        this.airDay = airDay;
    }

    /**
     * Returns the TV show air time in the format of hh:mma
     *
     * @return the TV show air time
     */
    public String getAirTime() {
        return airTime;
    }

    /**
     * Sets the TV show air time in the format of hh:mma
     *
     * @param airTime
     *         the TV show air time
     */
    public void setAirTime(final String airTime) {
        this.airTime = airTime;
    }

    /**
     * Returns the TV show content rating certification
     *
     * @return the TV show certification
     */
    public String getCertification() {
        return certification;
    }

    /**
     * Sets the TV show content rating certification
     *
     * @param certification
     *         the TV show certification
     */
    public void setCertification(final String certification) {
        this.certification = certification;
    }

    /**
     * Returns the IMDB identifier of the TV show
     *
     * @return the TV show IMDB identifier
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Sets the IMDB identifier of the TV show
     *
     * @param imdbId
     *         the IMDB identifier
     */
    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * Returns the TVDB identifier of the TV show
     *
     * @return the TV show TVDB identifier
     */
    public int getTvdbId() {
        return tvdbId;
    }

    /**
     * Sets the TVDB identifier of the TV show
     *
     * @param tvdbId
     *         the TVDB identifier
     */
    public void setTvdbId(final int tvdbId) {
        this.tvdbId = tvdbId;
    }

    /**
     * Returns the TVRage identifier of the TV show
     *
     * @return the TV show TVRage identifier
     */
    public int getTvrageId() {
        return tvrageId;
    }

    /**
     * Sets the TVRage identifier of the TV show
     *
     * @param tvrageId
     *         the TVRage identifier
     */
    public void setTvrageId(int tvrageId) {
        this.tvrageId = tvrageId;
    }

    /**
     * Returns whether or not the TV show has ended
     *
     * @return {@code true} if the TV show has ended, {@code false} otherwise.
     */
    public boolean isEnded() {
        return ended;
    }

    /**
     * Marks or unmarks the TV has ended
     *
     * @param ended
     *         the ended flag
     */
    public void setEnded(final boolean ended) {
        this.ended = ended;
    }

    /**
     * Returns the TV show art-work
     *
     * @return the TV show art-work
     */
    public TvShowArt getImages() {
        return images;
    }

    /**
     * Sets the TV show art-work
     *
     * @param images
     *         the TV show art-work
     */
    public void setImages(final TvShowArt images) {
        this.images = images;
    }

    /**
     * Returns the TV show rating
     *
     * @return the TV show rating
     */
    public Rating getRating() {
        return ratings;
    }

    /**
     * Sets the TV show rating
     *
     * @param rating
     *         the TV show rating
     */
    public void setRating(final Rating rating) {
        this.ratings = rating;
    }

    /**
     * Returns the TV show genres
     *
     * @return the TV show genres
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets the TV show genres
     *
     * @param genres
     *         the TV show genres
     */
    public void setGenres(final Set<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Returns the TV show seasons
     *
     * @return the TV show seasons
     */
    public List<TvShowSeason> getSeasons() {
        return seasons;
    }

    /**
     * Sets the seasons of the TV show
     *
     * @param seasons
     *         the TV show seasons
     */
    public void setSeasons(final List<TvShowSeason> seasons) {
        this.seasons = seasons;
    }

}
