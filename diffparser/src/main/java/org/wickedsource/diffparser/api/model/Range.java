package org.wickedsource.diffparser.api.model;

/**
 * Represents a range of line numbers that spans a window on a text file.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class Range {

    private final int lineStart;

    private final int lineEnd;

    public Range(int lineStart, int lineEnd) {
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
    }

    /**
     * The line number at which this range starts (inclusive).
     *
     * @return the line number at which this range starts.
     */
    public int getLineStart() {
        return lineStart;
    }

    /**
     * The line number at which this range ends (inclusive).
     *
     * @return the line number at which this range ends.
     */
    public int getLineEnd() {
        return lineEnd;
    }

}
