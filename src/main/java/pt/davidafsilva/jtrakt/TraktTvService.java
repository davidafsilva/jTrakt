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

	@GET("/search/shows.json/{apiKey}")
	List<TvShow> searchShow(@Query("query") String show);

	@GET("/search/shows.json/{apiKey}")
	List<TvShow> searchShow(@Query("query") String show,
	                        @Query("limit") int limit);

	@GET("/search/shows.json/{apiKey}")
	List<TvShow> searchShow(@Query("query") String show,
	                        @Query("limit") int limit,
	                        @Query("seasons") boolean includeSeasons);

	@GET("/genres/shows.json/{apiKey}")
	List<Genre> getGenres();

	@GET("/show/summary.json/{apiKey}/{identifier}")
	TvShowSummary getShowSummary(@Path("identifier") String showIdentifier)
            throws NoResultsFoundException;

	@GET("/show/summary.json/{apiKey}/{identifier}/{extended}")
	TvShowSummary getShowSummary(@Path("identifier") String showIdentifier,
	                             @Path("extended") boolean extended)
            throws NoResultsFoundException;

	@GET("/show/seasons.json/{apiKey}/{identifier}")
	List<TvShowSeason> getShowSeasons(@Path("identifier") String showIdentifier)
            throws NoResultsFoundException;

	@GET("/show/season.json/{apiKey}/{identifier}/{season}")
	List<TvShowSeasonEpisode> getShowEpisodes(@Path("identifier") String showIdentifier,
                                               @Path("season") int seasonNumber)
            throws NoResultsFoundException;

    @GET("/show/episode/summary.json/{apiKey}/{identifier}/{season}/{episode}")
    TvShowEpisodeSummary getShowEpisodeSummary(@Path("identifier") String showIdentifier,
                                               @Path("season") int seasonNumber,
                                               @Path("episode") int episodeNumber)
            throws NoResultsFoundException;
}
