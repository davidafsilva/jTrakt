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

/**
 * This class represents a TV show artwork.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   ...
 *   "images": {
 *     "poster": "http://slurm.trakt.us/images/posters/23332.16.jpg",
 *     "fanart": "http://slurm.trakt.us/images/fanart/23332.16.jpg",
 *     "banner": "http://slurm.trakt.us/images/banners/23332.16.jpg"
 *   },
 *   ...
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class TvShowArt extends BaseModel {

    // properties
    private String banner;
    private String fanArt;
    private String poster;

    /**
     * Returns the TV show banner URL
     *
     * @return the banner URL
     */
    public String getBanner() {
        return banner;
    }

    /**
     * Sets the TV show banner URL
     *
     * @param banner
     *         the banner URL
     */
    public void setBanner(final String banner) {
        this.banner = banner;
    }

    /**
     * Returns the TV show fan-artwork URL
     *
     * @return the fan-artwork URL
     */
    public String getFanArt() {
        return fanArt;
    }

    /**
     * Sets the TV show fan-artwork URL
     *
     * @param fanArt
     *         the fan-artwork URL
     */
    public void setFanArt(final String fanArt) {
        this.fanArt = fanArt;
    }

    /**
     * Returns the TV show poster URL
     *
     * @return the poster URL
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Sets the TV show poster URL
     *
     * @param poster
     *         the poster URL
     */
    public void setPoster(final String poster) {
        this.poster = poster;
    }
}
