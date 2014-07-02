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

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a TV show season.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "season": 1,
 *   "episodes": 8,
 *   "url": "http://trakt.tv/show/silicon-valley/season/1",
 *   "poster": "http://slurm.trakt.us/images/seasons/23332-1.16.jpg",
 *   "images": {
 *     "poster": "http://slurm.trakt.us/images/seasons/23332-1.16.jpg"
 *   }
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class TvShowSeason extends BaseModel {

    // properties
    private int number;
    private int episodesNumber;
    private List<TvShowEpisode> episodes = new ArrayList<>();
    private String url;
    private TvShowArt images;

    /**
     * Returns the season's number
     *
     * @return the season's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the season's number
     *
     * @param number
     *         the season's number
     */
    public void setNumber(final int number) {
        this.number = number;
    }

    /**
     * Returns the total number of episodes in this season
     *
     * @return the total number of episode of this season
     */
    public int getEpisodesNumber() {
        return episodesNumber;
    }

    /**
     * Sets the total number of episodes for this seasons
     *
     * @param episodesNumber
     *         the total number of episodes
     */
    public void setEpisodesNumber(final int episodesNumber) {
        this.episodesNumber = episodesNumber;
    }

    /**
     * Returns the show episodes for this season
     *
     * @return the show episodes
     */
    public List<TvShowEpisode> getEpisodes() {
        return episodes;
    }

    /**
     * Sets the show episodes for this season
     *
     * @param episodes
     *         the show episodes
     */
    public void setEpisodes(final List<TvShowEpisode> episodes) {
        this.episodes = episodes;
    }

    /**
     * Returns the season's Trakt web URL
     *
     * @return the season's web URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the season's web URL
     *
     * @param url
     *         the season's web URL
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns the season's related art-work images
     *
     * @return the season's art-work
     */
    public TvShowArt getImages() {
        return images;
    }

    /**
     * Sets the season's related art-work
     *
     * @param images
     *         the art-work
     */
    public void setImages(final TvShowArt images) {
        this.images = images;
    }
}
