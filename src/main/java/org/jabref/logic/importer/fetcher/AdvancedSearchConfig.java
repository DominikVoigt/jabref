package org.jabref.logic.importer.fetcher;

import java.util.Objects;

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

        public AdvancedSearchConfigBuilder anyField(String anyField) {
            this.defaultField = Objects.requireNonNull(anyField);
            return this;
        }

        public AdvancedSearchConfigBuilder author(String author) {
            this.author = Objects.requireNonNull(author);
            return this;
        }

        public AdvancedSearchConfigBuilder title(String title) {
            this.title = Objects.requireNonNull(title);
            return this;
        }

        public AdvancedSearchConfigBuilder fromYear(int fromYear) {
            this.fromYear = fromYear;
            return this;
        }

        public AdvancedSearchConfigBuilder toYear(int toYear) {
            this.toYear = toYear;
            return this;
        }

        public AdvancedSearchConfigBuilder journal(String journal) {
            this.journal = Objects.requireNonNull(journal);
            return this;
        }

        /**
         * Instantiates the AdvancesSearchConfig from the provided Builder parameters
         * If all text fields are empty an empty optional is returned
         *
         * @return AdvancedSearchConfig instance with the fields set to the values defined in the building instance.
         * @throws IllegalStateException An IllegalStateException is thrown in case all text search fields are empty.
         *                               See: https://softwareengineering.stackexchange.com/questions/241309/builder-pattern-when-to-fail/241320#241320
         */
        public AdvancedSearchConfig build() throws IllegalStateException {
            if (textSearchFieldsAreEmpty()) {
                throw new IllegalStateException("Not all text fields may be empty!");
            } else {
                return new AdvancedSearchConfig(defaultField, author, title, fromYear, toYear, journal);
            }
        }

        private boolean textSearchFieldsAreEmpty() {
            return StringUtil.isBlank(defaultField) && StringUtil.isBlank(title) && StringUtil.isBlank(author) && StringUtil.isBlank(journal);
        }
    }
}
