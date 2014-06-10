package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowArt extends BaseModel {

	private String banner;
	private String fanArt;
	private String poster;

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getFanArt() {
		return fanArt;
	}

	public void setFanArt(String fanArt) {
		this.fanArt = fanArt;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
}
