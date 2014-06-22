package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowEpisodeSummary {

    private TvShow show;
    private TvShowSeasonEpisode episode;

    public TvShow getShow() {
        return show;
    }

    public void setShow(final TvShow show) {
        this.show = show;
    }

    public TvShowSeasonEpisode getEpisode() {
        return episode;
    }

    public void setEpisode(final TvShowSeasonEpisode episode) {
        this.episode = episode;
    }
}
