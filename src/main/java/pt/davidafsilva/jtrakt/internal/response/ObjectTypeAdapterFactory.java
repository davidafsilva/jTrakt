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
import pt.davidafsilva.jtrakt.model.TvShowSeason;
import pt.davidafsilva.jtrakt.model.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.TvShowSummary;

import java.util.HashMap;
import java.util.Map;

/**
 * This class defines the creator of the object deserialization entities.
 *
 * @author David Silva
 */
public enum ObjectTypeAdapterFactory implements TypeAdapterFactory {
    INSTANCE; // singleton

    // constructors map
    private final Map<Class<?>, AdapterConstructor> constructors =
            new HashMap<>();

    /**
     * Default constructor
     */
    private ObjectTypeAdapterFactory() {
        // build the constructors map
        constructors.put(TvShow.class, TvShowTypeAdapter::new);
        constructors.put(TvShowSummary.class, TvShowSummaryTypeAdapter::new);
        constructors.put(Genre.class, GenreTypeAdapter::new);
        constructors.put(Rating.class, RatingTypeAdapter::new);
        constructors.put(TvShowArt.class, TvShowArtTypeAdapter::new);
        constructors.put(People.class, PeopleTypeAdapter::new);
        constructors.put(Actor.class, ActorTypeAdapter::new);
        constructors.put(TvShowSeason.class, TvShowSeasonTypeAdapter::new);
        constructors.put(TvShowEpisode.class, TvShowEpisodeTypeAdapter::new);
        constructors.put(TvShowSeasonEpisode.class,
                         TvShowSeasonEpisodeTypeAdapter::new);
        constructors.put(TvShowEpisodeSummary.class,
                         TvShowEpisodeSummaryTypeAdapter::new);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        final TypeAdapter<T> adapter;

        // search for the constructor
        final AdapterConstructor constructor =
                constructors.get(type.getRawType());
        if (constructor != null) {
            adapter = (TypeAdapter<T>) constructor.create(gson);
        } else {
            // we don't support its
            adapter = null;
        }

        return adapter;
    }

    /**
     * Defines the contract of each adapter constructor jn order
     * to abstract the instantiation of a type adapter.
     */
    @FunctionalInterface
    private interface AdapterConstructor {
        TypeAdapter<?> create(final Gson gson);
    }
}
