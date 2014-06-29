package pt.davidafsilva.jtrakt.internal.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Predicate;

/**
 * This class defines a local cache mechanism which is associated with a given
 * thread.
 * <br/>
 * The purpose of this class is to define a small set of cache functionality
 * for
 * the
 * deserialization process, which is tied to a particular thread.
 *
 * @author David Silva
 * @see java.lang.ThreadLocal
 */
enum LocalCache {
    INSTANCE; // singleton

    // the cache
    private final ThreadLocal<Map<Class<?>, List<?>>> cache =
            ThreadLocal.<Map<Class<?>, List<?>>>withInitial(WeakHashMap::new);

    /**
     * Gets the cached objects of a given type for the current thread being
     * executed.
     *
     * @param clazz
     *         the class of the object
     * @param <T>
     *         the type of the object
     * @return the cached objects, if no objects are cached an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> get(Class<T> clazz) {
        final Map<Class<?>, List<?>> cache = this.cache.get();
        List<?> cachedObjects = cache.get(clazz);
        if (cachedObjects == null) {
            cachedObjects = new ArrayList<>();
            cache.put(clazz, cachedObjects);
        }

        return (List<T>) cachedObjects;
    }

    /**
     * Returns the first cached object of the given class that fulfills a given
     * restriction criteria.
     *
     * @param clazz
     *         the class of the object
     * @param restrictionPredicate
     *         the restriction predicate
     * @param <T>
     *         the type of the object
     * @return the first object that meets the restriction criteria, {@code
     *         null} if none is found.
     * @throws java.lang.NullPointerException
     *         if one of {@code clazz} or {@code restrictionPredicate} is
     *         {@code
     *         null}
     */
    <T> T getCachedObject(Class<T> clazz, Predicate<T> restrictionPredicate) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(restrictionPredicate);
        final List<T> cache = get(clazz);
        if (!cache.isEmpty()) {
            return cache.stream()
                        .findFirst()
                        .filter(restrictionPredicate)
                        .get();
        }

        return null;
    }

    /**
     * Caches the given object at the thread currently being executed.
     *
     * @param clazz
     *         the class of the object
     * @param object
     *         the object to cache
     * @param <T>
     *         the type of the object
     * @throws java.lang.NullPointerException
     *         if one of {@code clazz} or {@code object} is {@code null}
     */
    <T> void put(Class<T> clazz, T object) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(object);
        get(clazz).add(object);
    }

    /**
     * Cleans up the cache currently associated with the thread being executed.
     */
    void clean() {
        cache.remove();
    }
}
