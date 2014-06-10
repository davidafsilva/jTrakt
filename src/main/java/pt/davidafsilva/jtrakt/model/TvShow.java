package pt.davidafsilva.jtrakt.model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShow extends BaseModel {

	private String title;
	private int year;
	private String url;
	private LocalDateTime firstAired;
	private String country;
	private String overview;
	private int runtime;
	private String network;
	private String airDay;
	private String airTime;
	private String certification;
	private String imdbId;
	private int tvdbId;
	private int tvrageId;
	private boolean ended;
	private TvShowArt images;
	private Rating ratings;
	private Set<Genre> genres;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDateTime getFirstAired() {
		return firstAired;
	}

	public void setFirstAired(LocalDateTime firstAired) {
		this.firstAired = firstAired;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getAirDay() {
		return airDay;
	}

	public void setAirDay(String airDay) {
		this.airDay = airDay;
	}

	public String getAirTime() {
		return airTime;
	}

	public void setAirTime(String airTime) {
		this.airTime = airTime;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public int getTvdbId() {
		return tvdbId;
	}

	public void setTvdbId(int tvdbId) {
		this.tvdbId = tvdbId;
	}

	public int getTvrageId() {
		return tvrageId;
	}

	public void setTvrageId(int tvrageId) {
		this.tvrageId = tvrageId;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public TvShowArt getImages() {
		return images;
	}

	public void setImages(TvShowArt images) {
		this.images = images;
	}

	public Rating getRatings() {
		return ratings;
	}

	public void setRatings(Rating ratings) {
		this.ratings = ratings;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
}
