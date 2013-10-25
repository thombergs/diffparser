package org.wickedsource.diffparser.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a "hunk" of changes made to a file.
 * <p/>
 * A Hunk consists of one or more lines that either exist only in the first file ("from line"), only in the second file ("to line") or in
 * both files ("neutral line"). Additionally, it contains information about which excerpts of the compared files are compared in this
 * Hunk in the form of line ranges.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public class Hunk {

    private Range fromFileRange;

    private Range toFileRange;

    private List<Line> lines = new ArrayList<>();

    /**
     * The range of line numbers that this Hunk spans in the first file of the Diff.
     *
     * @return range of line numbers in the first file (the "from" file).
     */
    public Range getFromFileRange() {
        return fromFileRange;
    }

    /**
     * The range of line numbers that this Hunk spans in the second file of the Diff.
     *
     * @return range of line numbers in the second file (the "to" file).
     */
    public Range getToFileRange() {
        return toFileRange;
    }

    /**
     * The lines that are part of this Hunk.
     *
     * @return lines of this Hunk.
     */
    public List<Line> getLines() {
        return lines;
    }

    public void setFromFileRange(Range fromFileRange) {
        this.fromFileRange = fromFileRange;
    }

    public void setToFileRange(Range toFileRange) {
        this.toFileRange = toFileRange;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
