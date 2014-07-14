jTrakt
=============
jTrakt is a simple Java API for accessing (selected) trakt.tv content.<br/>
Currently only a small set of methods are supported, feel free to drop a message
and request features to be implemented or submit a pull request.


Usage:
------
#### 1. Create/Initialize the TV service
```java
    final TraktTvService service = TraktTestServiceFactory.getInstance().createTvService(API_KEY);
```
Note: In order to acquire an API key, please create an account at [Trakt.tv](http://trakt.tv/).

#### 2. Get the first thirty (ordered by relevance) TV shows that matches "valley"
```java
    final List<TvShow> searchResult = service.searchShow("valley");
    // iterate over the results..
```

#### 3. Get the first five TV shows (ordered by relevance) that matches "valley"
```java
    final List<TvShow> searchResult = service.searchShow("valley", 5);
    // iterate over the results..
```

#### 4. Get the first TV show (ordered by relevance) that matches "silicon valley", including small seasons/episode count information
```java
    final List<TvShow> searchResult = service.searchShow("valley", 1, true);
    final TvShow show = searchResult.isEmpty() ? null : searchResult.get(0);
    if (show != null) {
	    // use the show for something
	}
```
	
#### 5. Get the show summary for Silicon Valley by TVDB identifier (show slug is also supported by the API)
```java
    try {
        final TvShowSummary showSummary = service.getShowSummary("277165");
        // use the show summary for something
    } catch (NoResultsFoundException e) {
        // handle error, invalid show...
    }
```

#### 6. Get the seasons information for Silicon Valley by TVDB identifier (show slug is also supported)
```java
    try {
        final List<TvShowSeason> showSeasons = service.getShowSeasons("277165");
        // use the show seasons for something
    } catch (NoResultsFoundException e) {
        // handle error, invalid show...
    }
```

#### 7. Get the episodes information for Silicon Valley, season 1 (use 0 for special episodes)
```java
    try {
        final List<TvShowSeasonEpisode> seasonEpisodes = service.getShowEpisodes("277165", 1);
        // use the season episodes for something
    } catch (NoResultsFoundException e) {
        // handle error, invalid show or season...
    }
```

#### 8. Get one episode information for Silicon Valley, season 1 (use 0 for special episodes), episode 1
```java
    try {
        final TvShowEpisodeSummary episode = service.getShowEpisodeSummary("277165", 1, 1);
        // use the episode for something
    } catch (NoResultsFoundException e) {
        // handle error, invalid show...
    }
```


Errors:
--------
Errors in the API queries, such as querying with an invalid show, season or episode will trigger an 
[NoResultsFoundException](https://github.com/davidafsilva/jTrakt/blob/master/src/main/java/pt/davidafsilva/jtrakt/exception/NoResultsFoundException.java). 
The cause of that lies in the implementation of the Trakt REST API, which will return error responses with 
HTTP status other than 200 when handling invalid data. That's why you'll see an  exception rather than a null 
object or empty collection.

    
TODO(?):
--------
* Movie Related services: TBD
* User Management Related: TBD


Copyright
---------
Copyright (c) 2014, David Silva<br/>
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
