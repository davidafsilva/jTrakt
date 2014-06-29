package pt.davidafsilva.jtrakt.model.tv;

import pt.davidafsilva.jtrakt.model.common.BaseModel;

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
