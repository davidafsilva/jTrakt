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
