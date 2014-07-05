/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *          * Redistributions of source code must retain the above copyright
 *              notice, this list of conditions and the following disclaimer.
 *          * Redistributions in binary form must reproduce the above copyright
 *              notice, this list of conditions and the following disclaimer
 *              in the
 *              documentation and/or other materials provided with the
 *              distribution.
 *          * Neither the name of the <organization> nor the
 *              names of its contributors may be used to endorse or promote
 *              products
 *              derived from this software without specific prior written
 *              permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package pt.davidafsilva.jtrakt.model.common;

/**
 * <p>This class represents a Rating.</p>
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
    public void setPercentage(final double percentage) {
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
    public void setVotes(final int votes) {
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
    public void setLoved(final int loved) {
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
    public void setHated(final int hated) {
        this.hated = hated;
    }
}

