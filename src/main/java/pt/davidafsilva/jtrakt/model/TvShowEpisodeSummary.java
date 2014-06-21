package pt.davidafsilva.jtrakt.model;

import java.time.LocalDateTime;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowEpisodeSummary extends TvShowEpisode {

	private int tvdbId;
	private String title;
	private String overview;
	private LocalDateTime firstAired;
	private String url;
	private String screenUrl;
	private Rating rating;
	//private TvShow show;

	public int getTvdbId() {
		return tvdbId;
	}

	public void setTvdbId(final int tvdbId) {
		this.tvdbId = tvdbId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(final String overview) {
		this.overview = overview;
	}

	public LocalDateTime getFirstAired() {
		return firstAired;
	}

	public void setFirstAired(final LocalDateTime firstAired) {
		this.firstAired = firstAired;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getScreenUrl() {
		return screenUrl;
	}

	public void setScreenUrl(final String screenUrl) {
		this.screenUrl = screenUrl;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(final Rating rating) {
		this.rating = rating;
	}
}
