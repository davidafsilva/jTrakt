package pt.davidafsilva.jtrakt.internal.cache;

import pt.davidafsilva.jtrakt.model.tv.TvShowSeason;

import java.util.Objects;

/**
 * This is a simple
 *
 * @author David Silva
 */
public enum DeserializationCache {
    INSTANCE; // singleton

    /**
     * Cleans up the cache currently associated with the thread being executed.
     */
    public void clean() {
        LocalCache.INSTANCE.clean();
    }

    /**
     * Caches the given object at the thread currently being executed.
     *
     * @param object
     *         the object to cache
     * @param <T>
     *         the type of the object
     * @throws java.lang.NullPointerException
     *         if {@code object} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public <T> void put(T object) {
        Objects.requireNonNull(object);
        final Class<T> clazz = (Class<T>) object.getClass();
        LocalCache.INSTANCE.put(clazz, object);
    }

    /**
     * <p>
     * Retrieves a season from the cache with the given season number.
     * </p>
     *
     * <p>
     * This is only useful if a single show is being deserialized, otherwise
     * one might get the season for the wrong show.
     * </p>
     *
     * @param seasonNumber
     *         the season number
     * @return a cached season, {@code null} if no season with the given number
     * is found.
     */
    public TvShowSeason getTvShowSeason(final int seasonNumber) {
        return LocalCache.INSTANCE.getCachedObject(TvShowSeason.class,
               season -> season.getNumber() == seasonNumber);
    }
}
