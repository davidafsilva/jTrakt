package pt.davidafsilva.jtrakt.model;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class TvShowSummary extends TvShow {

	private long lastUpdated;
	private People people;

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(final People people) {
		this.people = people;
	}
}
