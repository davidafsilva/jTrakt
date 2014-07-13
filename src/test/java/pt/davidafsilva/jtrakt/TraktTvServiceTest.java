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

package pt.davidafsilva.jtrakt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.davidafsilva.jtrakt.exception.NoResultsFoundException;
import pt.davidafsilva.jtrakt.model.common.Genre;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisodeSummary;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;

import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author David Silva
 */
public abstract class TraktTvServiceTest {

    private TraktTvService service;

    @Before
    public void setUp() throws Exception {
        service = setupService();
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    abstract TraktTvService setupService();

    @Test
    public void testSearchShow() throws Exception {
        final List<TvShow> tvShows = service.searchShow("Silicon Valley", 1);
        assertThat("Unexpected null result found", tvShows, notNullValue());
        assertThat("Expected 1 TV show result for \"Silicon Valley\"",
                   tvShows.size(), is(1));
        final TvShow show = tvShows.get(0);
        assertThat(show.getTitle(), is("Silicon Valley"));
    }

    @Test
    public void testSearchShow_noResults() throws Exception {
        final List<TvShow> tvShows = service.searchShow("noooooooresults");
        assertThat("Unexpected null result found", tvShows, notNullValue());
        assertThat("Expected no TV show results", tvShows.size(), is(0));
    }

    @Test
    public void testGenreRetrieval() throws Exception {
        final List<Genre> genres = service.getGenres();
        assertThat("No genres were found", genres, notNullValue());
        assertThat("Expected at least 1 genre", genres.size(), is(20));
    }

    @Test
    public void testShowSummary() throws Exception {
        final TvShowSummary showSummary = service.getShowSummary("277165");
        assertThat("No show summary found for \"Silicon Valley\"", showSummary,
                   notNullValue());
        assertThat("Invalid show expected", showSummary.getTitle(),
                   is("Silicon Valley"));
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowSummary_invalidShow() throws Exception {
        service.getShowSummary("0");
    }

    @Test
    public void testShowSeasons() throws Exception {
        final List<TvShowSeason> seasons = service.getShowSeasons("277165");
        assertThat("No seasons found for \"Silicon Valley\"", seasons,
                   notNullValue());
        assertThat("Expected at least 2 seasons for \"Silicon Valley\"",
                   seasons.size(), allOf(not(0), not(1)));
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowSeasons_invalidShow() throws Exception {
        service.getShowSeasons("0");
    }

    @Test
    public void testShowEpisodes() throws Exception {
        final List<TvShowSeasonEpisode> episodes =
                service.getShowEpisodes("277165", 1);
        assertThat("No episodes found for \"Silicon Valley\", season one",
                   episodes, notNullValue());
        assertThat("Expected 8 episodes for season one of \"Silicon Valley\"",
                   episodes.size(), is(8));
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowEpisodes_invalidShow() throws Exception {
        service.getShowEpisodes("0", 1);
    }

    @Test
    public void testShowEpisodes_invalidSeason() throws Exception {
        final List<TvShowSeasonEpisode> episodes =
                service.getShowEpisodes("277165", 999);
        assertThat("Expected non-null empty episodes collection", episodes,
                   notNullValue());
        assertThat("Expected 0 episodes for season 999 of \"Silicon Valley\"",
                   episodes.size(), is(0));
    }

    @Test
    public void testShowEpisodeSummary() throws Exception {
        final TvShowEpisodeSummary episodeSummary =
                service.getShowEpisodeSummary("277165", 1, 1);
        assertThat("No episode summary for \"Silicon Valley\", season one, " +
                   "episode one.", episodeSummary, notNullValue());
        assertThat("No show found in episode summary", episodeSummary.getShow(),
                   notNullValue());
        assertThat(episodeSummary.getShow().getTitle(), is("Silicon Valley"));
        assertThat("No episode found in episode summary",
                   episodeSummary.getEpisode(), notNullValue());
        assertThat(episodeSummary.getEpisode().getNumber(), is(1));
        assertThat(episodeSummary.getEpisode().getSeason().getNumber(), is(1));
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowEpisodeSummary_invalidShow() throws Exception {
        service.getShowEpisodeSummary("0", 1, 1);
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowEpisodeSummary_invalidSeason() throws Exception {
        service.getShowEpisodeSummary("277165", 999, 1);
    }

    @Test(expected = NoResultsFoundException.class)
    public void testShowEpisodeSummary_invalidEpisode() throws Exception {
        service.getShowEpisodeSummary("277165", 1, 999);
    }
}
