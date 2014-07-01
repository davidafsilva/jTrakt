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
