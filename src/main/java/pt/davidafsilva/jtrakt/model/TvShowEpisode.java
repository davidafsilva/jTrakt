package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowEpisode extends BaseModel {

	private int number;
	private TvShowSeason season;

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public TvShowSeason getSeason() {
		return season;
	}

	public void setSeason(final TvShowSeason season) {
		this.season = season;
	}
}
