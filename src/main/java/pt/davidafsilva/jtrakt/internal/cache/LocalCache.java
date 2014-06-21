package pt.davidafsilva.jtrakt.internal.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.Predicate;

/**
 * TODO: change me
 *
 * @author David Silva
 */
enum LocalCache {
	INSTANCE;

	private final ThreadLocal<Map<Class<?>, List<?>>> cache =
			ThreadLocal.<Map<Class<?>, List<?>>>withInitial(WeakHashMap::new);

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

	<T> T getCachedObject(Class<T> clazz, Predicate<T> restrictionPredicate) {
		Objects.requireNonNull(clazz);
        final List<T> cache = get(clazz);
        if (!cache.isEmpty())
            return cache.stream().findFirst().filter(restrictionPredicate).get();

        return null;
	}

	<T> void put(Class<T> clazz, T object) {
		Objects.requireNonNull(clazz);
		Objects.requireNonNull(object);
		get(clazz).add(object);
	}

	void clean() {
		cache.remove();
	}
}
