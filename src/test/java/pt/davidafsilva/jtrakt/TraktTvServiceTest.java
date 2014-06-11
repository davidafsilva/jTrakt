package pt.davidafsilva.jtrakt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.davidafsilva.jtrakt.model.Genre;
import pt.davidafsilva.jtrakt.model.TvShow;
import pt.davidafsilva.jtrakt.model.TvShowSeason;
import pt.davidafsilva.jtrakt.model.TvShowSummary;

import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * TODO: change me
 *
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
		final List<TvShow> tvShows = service.searchShow("Silicon Valley", 1, true);
		assertThat("No results found for \"Silicon Valley\"", tvShows, notNullValue());
		assertThat("Excepted 1 TV show result for \"Silicon Valley\"", tvShows.size(), is(1));
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
		assertThat("No show summary found for \"Silicon Valley\"", showSummary, notNullValue());
		assertThat("Invalid show expected", showSummary.getTitle(), is("Silicon Valley"));
	}

	@Test
	public void testShowSeasons() throws Exception {
		final List<TvShowSeason> seasons = service.getShowSeasons("277165");
		assertThat("No seasons found for \"Silicon Valley\"", seasons, notNullValue());
		assertThat("Excepted at least 2 seasons for \"Silicon Valley\"", seasons.size(), allOf(not(0), not(1)));
	}
}
