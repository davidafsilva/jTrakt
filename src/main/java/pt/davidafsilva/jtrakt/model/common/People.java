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

import java.util.HashSet;
import java.util.Set;

/**
 * <p>This class represents a People container.</p>
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
