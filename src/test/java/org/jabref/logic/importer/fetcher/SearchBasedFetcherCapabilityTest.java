package org.jabref.logic.importer.fetcher;

import org.jabref.logic.importer.FetcherException;

import org.junit.jupiter.api.Test;

/**
 * Defines the set of capability tests that each tests a given search capability, e.g. author based search.
 */
interface SearchBasedFetcherCapabilityTest {

    @Test
    void authorSearch() throws FetcherException;

    @Test
    void yearSearch() throws FetcherException;

    @Test
    void yearRangeSearch() throws FetcherException;

    @Test
    void journalSearch() throws FetcherException;

    @Test
    void phraseSearch() throws FetcherException;

    /**
     * Test boolean AND connection
     *
     * @throws FetcherException
     */
    @Test
    void authorAndTitleSearch() throws FetcherException;
}
