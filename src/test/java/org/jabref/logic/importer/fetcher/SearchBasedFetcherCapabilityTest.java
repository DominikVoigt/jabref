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
    void authorSearch() throws Exception;

    /**
     * Test whether the library API supports year field search.
     */
    @Test
    void yearSearch() throws Exception;

    /**
     * Test whether the library API supports year range search.
     */
    @Test
    void yearRangeSearch() throws Exception;

    /**
     * Test whether the library API supports journal based search.
     */
    @Test
    void journalSearch() throws Exception;

    /**
     * Test whether the library API supports phrase search.
     */
    @Test
    void phraseSearch() throws Exception;

    /**
     * Test whether the library API supports boolean AND connection in queries.
     */
    @Test
    void authorAndTitleSearch() throws Exception;
}
