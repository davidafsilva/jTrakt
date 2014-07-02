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

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * The HTTP (mock) client
 *
 * @author David Silva
 */
public class MockClient implements Client {

    private final Map<String, MockResponse> responseMap;

    MockClient(final Map<String, MockResponse> responseMap) {
        this.responseMap = responseMap;
    }

    @Override
    public Response execute(Request request) throws IOException {
        final URI uri = URI.create(request.getUrl());

        final MockResponse response = responseMap.get(getKey(uri));
        if (response == null) {
            throw new IllegalStateException(
                    String.format("expected a request path: %s",
                                  uri.getPath()));
        }

        return response.response(request);
    }

    private String getKey(URI uri) {
        final StringBuilder sb = new StringBuilder();
        sb.append(uri.getPath());
        if (uri.getQuery() != null && !uri.getQuery().isEmpty()) {
            sb.append("?");
            sb.append(uri.getQuery());
        }

        return sb.toString();
    }
}
