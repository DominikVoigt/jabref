package org.jabref.logic.importer.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.jabref.model.entry.LinkedFile;

public class FileFieldParser {

    public static List<LinkedFile> parse(String value) {
        List<LinkedFile> files = new ArrayList<>();

        if ((value == null) || value.trim().isEmpty()) {
            return files;
        }

        List<String> entry = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inXmlChar = false;
        boolean escaped = false;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (!escaped && (c == '\\')) {
                escaped = true;
                continue;
            } else if (!escaped && (c == '&') && !inXmlChar) {
                // Check if we are entering an XML special character construct such
                // as "&#44;", because we need to know in order to ignore the semicolon.
                sb.append(c);
                if ((value.length() > (i + 1)) && (value.charAt(i + 1) == '#')) {
                    inXmlChar = true;
                }
            } else if (!escaped && inXmlChar && (c == ';')) {
                // Check if we are exiting an XML special character construct:
                sb.append(c);
                inXmlChar = false;
            } else if (!escaped && (c == ':')) {
                entry.add(sb.toString());
                sb = new StringBuilder();
            } else if (!escaped && (c == ';') && !inXmlChar) {
                entry.add(sb.toString());
                sb = new StringBuilder();

                files.add(convert(entry));
            } else {
                sb.append(c);
            }
            escaped = false;
        }
        if (sb.length() > 0) {
            entry.add(sb.toString());
        }

        if (!entry.isEmpty()) {
            files.add(convert(entry));
        }

        return files;
    }

    private static LinkedFile convert(List<String> entry) {
        // ensure list has at least 3 fields
        while (entry.size() < 3) {
            entry.add("");
        }
        LinkedFile field;
        try {
            field = new LinkedFile(entry.get(0), Path.of(entry.get(1)), entry.get(2));
        } catch (InvalidPathException ex) {
            // Might be a URL not a Path
            try {
                field = new LinkedFile(new URL(entry.get(1)), entry.get(2));
            } catch (MalformedURLException e) {
                // Otherwise just throw the InvalidPathException
                throw ex;
            }
        }
        // link is only mandatory field
        if (field.getDescription().isEmpty() && field.getLink().isEmpty() && !field.getFileType().isEmpty()) {
            field = new LinkedFile("", Path.of(field.getFileType()), "");
        } else if (!field.getDescription().isEmpty() && field.getLink().isEmpty() && field.getFileType().isEmpty()) {
            field = new LinkedFile("", Path.of(field.getDescription()), "");
        }
        entry.clear();
        return field;
    }
}
