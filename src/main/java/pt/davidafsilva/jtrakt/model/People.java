package pt.davidafsilva.jtrakt.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public class People extends BaseModel {

	private Set<Actor> actors = new HashSet<>();

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(final Set<Actor> actors) {
		this.actors = actors;
	}
}
