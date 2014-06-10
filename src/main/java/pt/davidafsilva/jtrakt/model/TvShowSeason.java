package pt.davidafsilva.jtrakt.model;

import java.util.List;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowSeason extends BaseModel {

	private int number;
	private int episodesNumber;
	private List<TvShowEpisode> episodes;
	private String url;
	private TvShowArt images;
}
