package pt.davidafsilva.jtrakt.model;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowSeason extends BaseModel {

	private int number;
	private int episodesNumber;
	private List<TvShowEpisode> episodes = new ArrayList<>();
	private String url;
	private TvShowArt images;

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public int getEpisodesNumber() {
		return episodesNumber;
	}

	public void setEpisodesNumber(final int episodesNumber) {
		this.episodesNumber = episodesNumber;
	}

	public List<TvShowEpisode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(final List<TvShowEpisode> episodes) {
		this.episodes = episodes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public TvShowArt getImages() {
		return images;
	}

	public void setImages(final TvShowArt images) {
		this.images = images;
	}
}
