/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *          * Redistributions of source code must retain the above copyright
 *              notice, this list of conditions and the following disclaimer.
 *          * Redistributions in binary form must reproduce the above copyright
 *              notice, this list of conditions and the following disclaimer in the
 *              documentation and/or other materials provided with the distribution.
 *          * Neither the name of the <organization> nor the
 *              names of its contributors may be used to endorse or promote products
 *              derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
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

package pt.davidafsilva.jtrakt.client;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The HTTP (mock) client builder
 *
 * @author David Silva
 */
public class ClientBuilder {

    private final Map<String, MockResponse> responseMap = new HashMap<>();

    public PathResponsePairBuilder whenRequest(final String path) {
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        return new PathResponsePairBuilder(path);
    }

    public MockClient build() {
        if (responseMap.isEmpty()) {
            throw new IllegalStateException(
                    "no responses were mapped for the client");
        }
        return new MockClient(responseMap);
    }

    public class PathResponsePairBuilder {
        private String path;
        private int httpCode = HttpURLConnection.HTTP_OK;
        private StringBuilder queryParameters;

        private PathResponsePairBuilder(final String path) {
            this.path = path;
        }

        public PathResponsePairBuilder code(final int httpCode) {
            this.httpCode = httpCode;
            return this;
        }

        public PathResponsePairBuilder withParameter(final String key,
                                                     final String value) {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);

            if (queryParameters == null) {
                queryParameters = new StringBuilder();
            } else {
                queryParameters.append("&");
            }
            queryParameters.append(key);
            queryParameters.append("=");
            try {
                queryParameters.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // should not happen, ever!
                queryParameters.append(value);
            }
            return this;
        }

        private String getResponseKey() {
            final StringBuilder keyBuilder = new StringBuilder();
            keyBuilder.append(path);
            if (queryParameters != null) {
                keyBuilder.append("?");
                keyBuilder.append(queryParameters.toString());
            }

            return keyBuilder.toString();
        }

        public ClientBuilder reply(final String jsonResponse) {
            Objects.requireNonNull(jsonResponse);
            responseMap.put(getResponseKey(),
                            new ServerResponse(httpCode, jsonResponse));
            return ClientBuilder.this;
        }
    }

}
