package pt.davidafsilva.jtrakt.model.common;

/**
 * This class represents an actor.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   "name": "Thomas Middleditch",
 *   "character": "Richard",
 *   "images": {
 *     "headshot": "http://slurm.trakt.us/images/avatar-large.jpg"
 *   }
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class Actor extends BaseModel {

    // properties
    private String name;
    private String character;
    private String pictureUrl;

    /**
     * Returns the name of the actor
     *
     * @return the name of the actor
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the actor
     *
     * @param name
     *         the actor's name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the actor character name
     *
     * @return the actor's character name
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Sets the actor character name
     *
     * @param character
     *         the actor's character name
     */
    public void setCharacter(final String character) {
        this.character = character;
    }

    /**
     * Returns the photo URL of the actor
     *
     * @return the URL of the actor's photo
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * Sets the photo URL of the actor
     *
     * @param pictureUrl
     *         the URL of the actor's photo
     */
    public void setPictureUrl(final String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Actor actor = (Actor) o;

        if (!character.equals(actor.character)) {
            return false;
        }
        if (!name.equals(actor.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + character.hashCode();
        return result;
    }
}
