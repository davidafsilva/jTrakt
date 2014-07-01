package pt.davidafsilva.jtrakt.model.tv;

import pt.davidafsilva.jtrakt.model.common.BaseModel;

/**
 * This class represents a TV show episode.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "season": 1,
 *   "number": 8
 * }
 * </pre>
 *
 * @author David Silva
 */
public class TvShowEpisode extends BaseModel {

    // properties
    private int number;
    private TvShowSeason season;

    /**
     * Returns the episode number
     *
     * @return the episode number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the episode number
     *
     * @param number
     *         the episode number
     */
    public void setNumber(final int number) {
        this.number = number;
    }

    /**
     * Returns the season of this episode
     *
     * @return the season's episode
     */
    public TvShowSeason getSeason() {
        return season;
    }

    /**
     * Sets the season of this episode
     *
     * @param season
     *         the season's episode
     */
    public void setSeason(final TvShowSeason season) {
        this.season = season;
    }
}
