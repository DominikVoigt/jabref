package org.jabref.logic.importer.fetcher;

import org.junit.jupiter.api.Test;

/**
 * Defines the set of capability tests that each tests a given search capability, e.g. author based search.
 */
interface SearchBasedFetcherCapabilityTest {

    /**
     * Test whether the library API supports author field search.
     */
    @Test
    void supportsAuthorSearch() throws Exception;

    /**
     * Test whether the library API supports year field search.
     */
    @Test
    void supportsYearSearch() throws Exception;

    /**
     * Test whether the library API supports year range search.
     */
    @Test
    void supportsYearRangeSearch() throws Exception;

    /**
     * Test whether the library API supports journal based search.
     */
    @Test
    void supportsJournalSearch() throws Exception;

    /**
     * Test whether the library API supports phrase search.
     */
    @Test
    void supportsPhraseSearch() throws Exception;

    /**
     * Test whether the library API supports boolean AND connection in queries.
     */
    @Test
    void supportsBooleanANDSearch() throws Exception;
}
