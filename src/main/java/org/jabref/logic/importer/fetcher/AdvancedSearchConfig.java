package org.jabref.logic.importer.fetcher;

import org.jabref.model.strings.StringUtil;

public class AdvancedSearchConfig {
    private final String defaultField;
    private final String author;
    private final String title;
    private final int fromYear;
    private final int toYear;
    private final String journal;

    AdvancedSearchConfig(String defaultField, String author, String title, int fromYear, int toYear, String journal) {
        this.defaultField = defaultField;
        this.author = author;
        this.title = title;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.journal = journal;
    }

    public boolean areTextSearchFieldsEmpty() {
        return StringUtil.isBlank(defaultField) && StringUtil.isBlank(title) && StringUtil.isBlank(author) && StringUtil.isBlank(journal);
    }

    public String getDefaultField() {
        return defaultField;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public String getJournal() {
        return journal;
    }

    public static AdvancedSearchConfigBuilder builder() {
        return new AdvancedSearchConfigBuilder();
    }

    public static class AdvancedSearchConfigBuilder {
        private String defaultField = "";
        private String author = "";
        private String title = "";
        private int fromYear;
        private int toYear;
        private String journal = "";

        public AdvancedSearchConfigBuilder() {
        }

        /**
         * @param anyField Search string for any field
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder anyField(String anyField) {
            this.defaultField = anyField;
            return this;
        }

        /**
         * @param author Search string for author field
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder author(String author) {
            this.author = author;
            return this;
        }

        /**
         * @param title Search string for title field
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * @param fromYear Search string for year search (lower bound)
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder fromYear(int fromYear) {
            this.fromYear = fromYear;
            return this;
        }

        /**
         * @param toYear Search string for year search (upper bound)
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder toYear(int toYear) {
            this.toYear = toYear;
            return this;
        }

        /**
         * @param journal Search string for journal field search
         * @return Builder instance
         */
        public AdvancedSearchConfigBuilder journal(String journal) {
            this.journal = journal;
            return this;
        }

        /**
         * Instantiates the AdvancesSearchConfig from the provided Builder parameters
         *
         * @return AdvancedSearchConfig instance with the fields set to the values defined in the building instance.
         */
        public AdvancedSearchConfig build() {
            return new AdvancedSearchConfig(defaultField, author, title, fromYear, toYear, journal);
        }
    }
}
