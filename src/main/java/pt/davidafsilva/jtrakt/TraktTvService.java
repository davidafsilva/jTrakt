package pt.davidafsilva.jtrakt;

import pt.davidafsilva.jtrakt.model.Genre;
import pt.davidafsilva.jtrakt.model.TvShow;
import pt.davidafsilva.jtrakt.model.TvShowSeason;
import pt.davidafsilva.jtrakt.model.TvShowSummary;
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
	public List<TvShow> searchShow(@Query("query") String show,
	                       @Query("limit") Integer limit,
	                       @Query("seasons") Boolean includeSeasons);

	@GET("/genres/shows.json/{apiKey}")
	public List<Genre> getGenres();

	@GET("/show/summary.json/{apiKey}/{identifier}")
	public TvShowSummary getShowSummary(@Path("identifier") String showIdentifier);

	@GET("/show/summary.json/{apiKey}/{identifier}/{extended}")
	public TvShowSummary getShowSummary(@Path("identifier") String showIdentifier,
	                                    @Path("extended") boolean extended);

	@GET("/show/seasons.json/{apiKey}/{identifier}")
	public List<TvShowSeason> getShowSeasons(@Path("identifier") String showIdentifier);
}
