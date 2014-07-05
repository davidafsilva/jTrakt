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
 * <p>This class represents an TV show/movie genre.</p>
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
