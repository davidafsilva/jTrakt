package pt.davidafsilva.jtrakt.model.common;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a People container.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   ...
 *   "people": {
 *     "actors": [
 *       "name": "Thomas Middleditch",
 *       "character": "Richard",
 *       "images": {
 *         "headshot": "http://slurm.trakt.us/images/avatar-large.jpg"
 *       },
 *       ..
 *     ]
 *   }
 *   ...
 * }
 * </pre>
 * Currently this container only contains Actors.
 *
 * @author David Silva
 */
public final class People extends BaseModel {

    // properties
    private Set<Actor> actors = new HashSet<>();

    /**
     * Returns the set of actors
     *
     * @return the set of actors
     */
    public Set<Actor> getActors() {
        return actors;
    }

    /**
     * Sets the actors of this container
     *
     * @param actors
     *         the actors
     */
    public void setActors(final Set<Actor> actors) {
        this.actors = actors;
    }
}
