package pt.davidafsilva.jtrakt;

import pt.davidafsilva.jtrakt.exception.NoResultsFoundException;
import pt.davidafsilva.jtrakt.model.common.Genre;
import pt.davidafsilva.jtrakt.model.tv.TvShow;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisodeSummary;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * This interface details the contract for the Trakt TV service
 * implementation.
 *
 * @author David Silva
 */
public interface TraktTvService {

    /**
     * Searches for TV shows that matches a given search criteria.<br/>
     * Since no search limit is being applied, defaults to 30. Please
     * use {@link #searchShow(String, int)} in order to supply your own limit.
     *
     * @param show
     *         the search query that should be used.
     * @return the search result with the matches for the search criteria
     * @see #searchShow(String, int)
     * @see #searchShow(String, int, boolean)
     */
    @GET("/search/shows.json/{apiKey}")
    List<TvShow> searchShow(@Query("query") String show);

    /**
     * Searches for TV shows that matches a given search criteria limiting
     * the number of results that are returned.
     *
     * @param show
     *         the search query that should be used.
     * @param limit
     *         the maximum number of results to be returned
     * @return the search result with the matches for the search criteria
     * @see #searchShow(String)
     * @see #searchShow(String, int, boolean)
     */
    @GET("/search/shows.json/{apiKey}")
    List<TvShow> searchShow(@Query("query") String show, @Query(
            "limit") int limit);

    /**
     * Searches for TV shows that matches a given search criteria limiting
     * the number of results that are returned, including a short seasons
     * information if {@code includeSeasons} is set to {@code true}.
     *
     * @param show
     *         the search query that should be used.
     * @param limit
     *         the maximum number of results to be returned
     * @param includeSeasons
     *         include a short season and episode information
     * @return the search result with the matches for the search criteria
     * @see #searchShow(String)
     * @see #searchShow(String, int)
     */
    @GET("/search/shows.json/{apiKey}")
    List<TvShow> searchShow(@Query("query") String show, @Query(
            "limit") int limit, @Query("seasons") boolean includeSeasons);

    /**
     * Gets all the possible genres for the TV shows
     *
     * @return all the possible genres
     */
    @GET("/genres/shows.json/{apiKey}")
    List<Genre> getGenres();

    @GET("/show/summary.json/{apiKey}/{identifier}")
    TvShowSummary getShowSummary(@Path("identifier") String showIdentifier)
            throws NoResultsFoundException;

    @GET("/show/summary.json/{apiKey}/{identifier}/{extended}")
    TvShowSummary getShowSummary(@Path(
            "identifier") String showIdentifier, @Path(
            "extended") boolean extended) throws NoResultsFoundException;

    @GET("/show/seasons.json/{apiKey}/{identifier}")
    List<TvShowSeason> getShowSeasons(@Path("identifier") String showIdentifier)
            throws NoResultsFoundException;

    @GET("/show/season.json/{apiKey}/{identifier}/{season}")
    List<TvShowSeasonEpisode> getShowEpisodes(@Path(
            "identifier") String showIdentifier, @Path(
            "season") int seasonNumber) throws NoResultsFoundException;

    @GET("/show/episode/summary.json/{apiKey}/{identifier}/{season}/{episode}")
    TvShowEpisodeSummary getShowEpisodeSummary(@Path(
            "identifier") String showIdentifier, @Path(
            "season") int seasonNumber, @Path("episode") int episodeNumber)
            throws NoResultsFoundException;
}
