package org.jabref.logic.importer.fetcher;

import org.jabref.logic.importer.FetcherException;

import org.junit.jupiter.api.Test;

/**
 * Defines the set of capability tests that each tests a given search capability, e.g. author based search.
 */
interface SearchBasedFetcherCapabilityTest {

    /**
     * Test whether the library API supports author field search.
     *
     * @throws Exception
     */
    @Test
    void authorSearch() throws Exception;

    /**
     * Test whether the library API supports year field search.
     *
     * @throws Exception
     */
    @Test
    void yearSearch() throws Exception;

    /**
     * Test whether the library API supports year range search.
     *
     * @throws Exception
     */
    @Test
    void yearRangeSearch() throws Exception;

    /**
     * Test whether the library API supports journal based search.
     *
     * @throws Exception
     */
    @Test
    void journalSearch() throws Exception;

    /**
     * Test whether the library API supports phrase search.
     *
     * @throws Exception
     */
    @Test
    void phraseSearch() throws Exception;

    /**
     * Test whether the library API supports boolean AND connection in queries.
     *
     * @throws FetcherException
     */
    @Test
    void authorAndTitleSearch() throws Exception;
}
