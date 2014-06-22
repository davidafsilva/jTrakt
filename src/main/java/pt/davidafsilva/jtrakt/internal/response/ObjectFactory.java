package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import pt.davidafsilva.jtrakt.model.Actor;
import pt.davidafsilva.jtrakt.model.Genre;
import pt.davidafsilva.jtrakt.model.People;
import pt.davidafsilva.jtrakt.model.Rating;
import pt.davidafsilva.jtrakt.model.TvShow;
import pt.davidafsilva.jtrakt.model.TvShowArt;
import pt.davidafsilva.jtrakt.model.TvShowEpisode;
import pt.davidafsilva.jtrakt.model.TvShowEpisodeSummary;
import pt.davidafsilva.jtrakt.model.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.TvShowSeason;
import pt.davidafsilva.jtrakt.model.TvShowSummary;

/**
 * TODO: change me
 *
 * @author David Silva
 */
public enum ObjectFactory implements TypeAdapterFactory {
	INSTANCE;

	@Override
	@SuppressWarnings("unchecked")
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		TypeAdapter<T> adapter = null;
		if (type.getRawType() == TvShow.class) {
			adapter = (TypeAdapter<T>) new TvShowTypeAdapter(gson);
		} else if (type.getRawType() == TvShowSummary.class) {
			adapter = (TypeAdapter<T>) new TvShowSummaryTypeAdapter(gson);
		} else if (type.getRawType() == Genre.class) {
			adapter = (TypeAdapter<T>) new GenreTypeAdapter(gson);
		} else if (type.getRawType() == Rating.class) {
			adapter = (TypeAdapter<T>) new RatingTypeAdapter(gson);
		} else if (type.getRawType() == TvShowArt.class) {
			adapter = (TypeAdapter<T>) new TvShowArtTypeAdapter(gson);
		} else if (type.getRawType() == People.class) {
			adapter = (TypeAdapter<T>) new PeopleTypeAdapter(gson);
		} else if (type.getRawType() == Actor.class) {
			adapter = (TypeAdapter<T>) new ActorTypeAdapter(gson);
		} else if (type.getRawType() == TvShowSeason.class) {
			adapter = (TypeAdapter<T>) new TvShowSeasonTypeAdapter(gson);
		} else if (type.getRawType() == TvShowEpisode.class) {
			adapter = (TypeAdapter<T>) new TvShowEpisodeTypeAdapter(gson);
		} else if (type.getRawType() == TvShowSeasonEpisode.class) {
			adapter = (TypeAdapter<T>) new TvShowSeasonEpisodeTypeAdapter(gson);
		} else if (type.getRawType() == TvShowEpisodeSummary.class) {
            adapter = (TypeAdapter<T>) new TvShowEpisodeSummaryTypeAdapter(gson);
        }

		return adapter;
	}
}
