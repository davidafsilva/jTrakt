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

package pt.davidafsilva.jtrakt.exception;

import retrofit.RetrofitError;

/**
 * <p>This exception represents a service query that returned
 * an error with no results.
 * </p>
 * Trakt API uses HTTP status codes to represent errors in some of their
 * responses, typically they'll send HTTP 400 (bad request) or HTTP 404
 * (not found) where queried with wrong or not found information.
 *
 * @author David Silva
 */
public final class NoResultsFoundException extends RuntimeException {

    /**
     * Default constructor.
     *
     * Constructs a new NoResultsFoundException with no cause and no message.
     */
    public NoResultsFoundException() {
        super();
    }

    /**
     * Constructs a new NoResultsFoundException with the
     * underlying error cause.
     *
     * @param cause
     *         the original retrofit error
     */
    public NoResultsFoundException(RetrofitError cause) {
        super(cause);
    }
}
