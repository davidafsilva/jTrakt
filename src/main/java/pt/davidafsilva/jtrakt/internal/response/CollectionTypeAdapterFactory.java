package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class defines the creator of the collection deserialization entities.
 *
 * @author David Silva
 */
public enum CollectionTypeAdapterFactory implements TypeAdapterFactory {
    INSTANCE; // singleton

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        final TypeAdapter<T> adapter;

        // check if it's actually assignable
        if (Collection.class.isAssignableFrom(type.getRawType())) {
            // get the collection element type
            final Type elementType = $Gson$Types.getCollectionElementType(
                    type.getType(), type.getRawType());

            // get the element type adapter
            final TypeAdapter<?> elementTypeAdapter = gson.getAdapter(
                    TypeToken.get(elementType));

            // instantiate the collection adapter
            if (type.getRawType() == List.class) {
                adapter = new CollectionTypeAdapter(gson,
                            (TypeAdapter) elementTypeAdapter, ArrayList::new);
            } else if (type.getRawType() == Set.class) {
                adapter = new CollectionTypeAdapter(gson,
                            (TypeAdapter) elementTypeAdapter, HashSet::new);
            } else {
                adapter = null;
            }
        } else {
            adapter = null;
        }

        return adapter;
    }

}
