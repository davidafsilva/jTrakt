package pt.davidafsilva.jtrakt.model.common;

/**
 * This class represents a Rating.
 * <br/>
 * It's the result of deserializing:
 * <pre>
 * {
 *   ...
 *   "ratings": {
 *     "percentage": 82,
 *     "votes": 919,
 *     "loved": 877,
 *     "hated": 42
 *   },
 *   ...
 * }
 * </pre>
 *
 * @author David Silva
 */
public final class Rating extends BaseModel {

    // properties
    private double percentage;
    private int votes;
    private int loved;
    private int hated;

    /**
     * Returns the rating percentage
     *
     * @return the rating percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * Sets the rating percentage
     *
     * @param percentage
     *         the rating percentage
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Returns the total amount of votes
     *
     * @return the total amount of votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Sets the total amount of votes
     *
     * @param votes
     *         the total votes
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * Returns the number of positive votes
     *
     * @return the number of positive votes
     */
    public int getLoved() {
        return loved;
    }

    /**
     * Sets the number of positive votes
     *
     * @param loved
     *         the positive votes count
     */
    public void setLoved(int loved) {
        this.loved = loved;
    }

    /**
     * Returns the number of negative votes
     *
     * @return the number of negative votes
     */
    public int getHated() {
        return hated;
    }

    /**
     * Sets the number of negative votes
     *
     * @param hated
     *         the negative votes count
     */
    public void setHated(int hated) {
        this.hated = hated;
    }
}

