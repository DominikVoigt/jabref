package org.jabref.logic.importer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.net.URLDownload;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.strings.StringUtil;

/**
 * This interface allows SearchBasedParserFetcher fetchers that implement it to test their corresponding library APIs for their advanced search options e.g. fielded search.
 */
public interface RawFetcher extends SearchBasedParserFetcher {

    /**
     * This method is used to send queries with arbitrary url parameters. This method is necessary as the performSearch method of the SearchBasedParserFetcher does not support arbitrary url parameters.
     *
     * @param urlParameters The given URL parameters as string should start with "?"
     * @return result of the query with the given URL parameters
     * @throws FetcherException
     * @throws MalformedURLException
     */
    default List<BibEntry> performRawSearch(String urlParameters) throws FetcherException, MalformedURLException {
        if (StringUtil.isBlank(urlParameters)) {
            return Collections.emptyList();
        }
        if (urlParameters.startsWith("?")) {
            urlParameters = urlParameters.substring(1);
        }

        // Replace white spaces with + to form a valid URL. No full URL encoding as it would encode "&" signs used for url parameters.
        try (InputStream stream = getRawUrlDownload(urlParameters.replace(" ", "+")).asInputStream()) {
            List<BibEntry> fetchedEntries = getParser().parseEntries(stream);

            fetchedEntries.forEach(this::doPostCleanup);

            return fetchedEntries;
        } catch (MalformedURLException e) {
            throw new FetcherException("Search URI is malformed", e);
        } catch (IOException e) {
            // TODO: Catch HTTP Response 401/403 errors and report that user has no rights to access resource
            throw new FetcherException("A network error occurred", e);
        } catch (ParseException e) {
            throw new FetcherException("An internal parser error occurred", e);
        }
    }

    /**
     * Create URL download for the given url parameters
     *
     * @param urlParameters the used url parameters, separate parameters should be concatenated by a "&". Should not begin with either "&" or "?".
     * @throws MalformedURLException Thrown if the given parameters are not formatted correctly.
     */
    URLDownload getRawUrlDownload(String urlParameters) throws MalformedURLException;
}
