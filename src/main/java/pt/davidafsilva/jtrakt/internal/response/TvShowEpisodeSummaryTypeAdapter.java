package pt.davidafsilva.jtrakt.internal.response;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pt.davidafsilva.jtrakt.model.tv.TvShowEpisodeSummary;
import pt.davidafsilva.jtrakt.model.tv.TvShowSeasonEpisode;
import pt.davidafsilva.jtrakt.model.tv.TvShowSummary;

import java.io.IOException;

/**
 * Deserialization entity for {@link TvShowEpisodeSummary} objects.
 *
 * @author David Silva
 */
final class TvShowEpisodeSummaryTypeAdapter extends ObjectTypeAdapter<TvShowEpisodeSummary> {

    /**
     * Default constructor for the type adapter
     *
     * @param gson
     * 		the GSON object
     */
    TvShowEpisodeSummaryTypeAdapter(final Gson gson) {
        super(gson);
    }

    private enum Fields {
        SHOW, EPISODE
    }

    @Override
    TvShowEpisodeSummary createInstance() {
        return new TvShowEpisodeSummary();
    }

    @Override
    void updateFieldValue(final TvShowEpisodeSummary object, final String fieldName, final JsonReader in)
            throws IOException {
        final String properFieldName = fieldName.toUpperCase();
        Fields field = null;
        try {
            field = Fields.valueOf(properFieldName);
        } catch (IllegalArgumentException e) {
            in.skipValue();
            logger.warning(String.format("Field %s not found.", fieldName));
        }

        if (field != null) {
            switch (field) {
                case SHOW:
                    // use TvShowSummary due to UTC first aired bit
                    object.setShow(readObject(in, TvShowSummary.class));
                    break;
                case EPISODE:
                    object.setEpisode(readObject(in, TvShowSeasonEpisode.class));
                    break;
                default:
                    in.skipValue();
                    logger.warning(String.format("Unmapped field: %s.", fieldName));
                    break;
            }
        }
    }
}
