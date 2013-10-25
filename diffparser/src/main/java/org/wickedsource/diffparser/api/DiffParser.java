package org.wickedsource.diffparser.api;

import org.wickedsource.diffparser.api.model.Diff;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Interface to a parser that parses a textual diff between two text files. See the javadoc of the implementation you want to use to see
 * what diff format it is expecting as input.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public interface DiffParser {

    /**
     * Constructs a list of Diffs from a textual InputStream.
     *
     * @param in the input stream to parse
     * @return list of Diff objects parsed from the InputStream.
     */
    List<Diff> parse(InputStream in);

    /**
     * Constructs a list of Diffs from a textual byte array.
     *
     * @param bytes the byte array to parse
     * @return list of Diff objects parsed from the byte array.
     */
    List<Diff> parse(byte[] bytes);

    /**
     * Constructs a list of Diffs from a textual File
     *
     * @param file the file to parse
     * @return list of Diff objects parsed from the File.
     */
    List<Diff> parse(File file) throws IOException;

}
