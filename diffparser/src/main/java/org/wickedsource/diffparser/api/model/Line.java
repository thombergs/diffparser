package org.wickedsource.diffparser.api.model;

/**
 * Represents a line of a Diff. A line is either contained in both files ("neutral"), only in the first file ("from"),
 * or only in the second file ("to").
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public class Line {

    /**
     * All possible types a line can have.
     */
    public enum LineType {

        /**
         * This line is only contained in the first file of the Diff (the "from" file).
         */
        FROM,

        /**
         * This line is only contained in the second file of the Diff (the "to" file).
         */
        TO,

        /**
         * This line is contained in both filed of the Diff, and is thus considered "neutral".
         */
        NEUTRAL

    }

    private final LineType lineType;

    private final String content;

    public Line(LineType lineType, String content) {
        this.lineType = lineType;
        this.content = content;
    }

    /**
     * The type of this line.
     *
     * @return the type of this line.
     */
    public LineType getLineType() {
        return lineType;
    }

    /**
     * The actual content of the line as String.
     *
     * @return the actual line content.
     */
    public String getContent() {
        return content;
    }

}
