package pt.davidafsilva.jtrakt.model.common;

/**
 * This class represents an TV show/movie genre.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   ...
 *   "genre": "Action",
 *   ...
 * }
 * </pre>
 * or
 * <pre>
 * {
 *   "name": "Action",
 *   "slug": "action"
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class Genre extends BaseModel {

    // properties
    private String name;
    private String slung;

    /**
     * Returns the genre name
     *
     * @return the genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the genre name
     *
     * @param name
     *         the genre name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the genre slung
     *
     * @return the genre slung
     */
    public String getSlung() {
        return slung;
    }

    /**
     * Sets the genre slung
     *
     * @param slung
     *         the genre slung
     */
    public void setSlung(final String slung) {
        this.slung = slung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Genre genre = (Genre) o;

        if (!name.equals(genre.name)) {
            return false;
        }
        if (!slung.equals(genre.slung)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + slung.hashCode();
        return result;
    }
}
