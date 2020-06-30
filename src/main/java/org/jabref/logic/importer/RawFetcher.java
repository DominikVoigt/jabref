package org.jabref.logic.importer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.importer.fetcher.AdvancedSearchConfig;
import org.jabref.logic.net.URLDownload;
import org.jabref.model.entry.BibEntry;

/**
 * This interface allows SearchBasedParserFetcher fetchers to test their corresponding
 * library APIs for their advanced search options, e.g. search in the "title" field.
 */
public interface RawFetcher extends SearchBasedParserFetcher {

    /**
     * This method is used to send queries with advanced URL parameters.
     * This method is necessary as the performSearch method does not support certain URL parameters that are used for
     * fielded search, such as a title, author, or year parameter.
     *
     * @param advancedSearchConfig the search config defining all fielded search parameters
     */
    default List<BibEntry> performRawSearch(AdvancedSearchConfig advancedSearchConfig) throws FetcherException {
        if (advancedSearchConfig.isEmpty()) {
            return Collections.emptyList();
        }
        // Replace white spaces with + to form valid URL parameters. No full URL encoding as it would encode "&" signs used for url parameters.
        try (InputStream stream = getRawURLDownload(advancedSearchConfig).asInputStream()) {
            List<BibEntry> fetchedEntries = getParser().parseEntries(stream);
            fetchedEntries.forEach(this::doPostCleanup);
            return fetchedEntries;
        } catch (MalformedURLException | URISyntaxException e) {
            throw new FetcherException("Search URI is malformed", e);
        } catch (IOException e) {
            // TODO: Catch HTTP Response 401/403 errors and report that user has no rights to access resource
            throw new FetcherException("A network error occurred", e);
        } catch (ParseException e) {
            throw new FetcherException("An internal parser error occurred", e);
        }
    }

    /**
     * TODO: Implement this with all parameters in all implementing classes.
     *
     * @param advancedSearchConfig the search config defining all fielded search parameters
     * @throws MalformedURLException Thrown if the given parameters are not formatted correctly.
     */
    URLDownload getRawURLDownload(AdvancedSearchConfig advancedSearchConfig) throws MalformedURLException, URISyntaxException;
}
