package org.jabref.logic.importer.fetcher;

import org.junit.jupiter.api.Test;

/**
 * Defines the set of capability tests that each tests a given search capability, e.g. author based search.
 * <p>
 * Certain capabilities are already tested elsewhere, and therefore not added here, e.g. DOI based search. List of not included capabilities: DOI, ISBN, ISSN, Fulltext, ID TODO: Decided what the test should do if the capability is not supported? => Maybe interface splitting/extension?
 */
interface SearchBasedFetcherCapabilityTest {

    /**
     * Tests whether the library the fetcher is associated with, supports title based search.
     */
    @Test
    void titleSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports author based search.
     */
    @Test
    void authorSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports year based search.
     */
    @Test
    void yearSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports searching the abstract.
     */
    @Test
    void abstractSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports subject based search.
     */
    @Test
    void subjectSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports searching for articles in a specific language.
     */
    @Test
    void languageSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports keyword based search.
     */
    @Test
    void keywordSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports search based on where the article is publicized in.
     */
    @Test
    void publicationSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports content type based search e.g. Journal,Book...
     */
    @Test
    void contentTypeSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports publisher based search.
     */
    @Test
    void publisherSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports phrase based search.
     */
    @Test
    void phraseSearch();

    /**
     * Tests whether the library the fetcher is associated with, supports institution based search. This means it finds articles of authors that are associated with the given institution.
     */
    @Test
    void institutionSearch();
}
