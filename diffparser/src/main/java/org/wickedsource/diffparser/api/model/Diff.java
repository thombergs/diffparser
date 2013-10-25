package org.wickedsource.diffparser.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Diff between two files.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
@SuppressWarnings("UnusedDeclaration")
public class Diff {

    private String fromFileName;

    private String toFileName;

    private List<String> headerLines = new ArrayList<>();

    private List<Hunk> hunks = new ArrayList<>();

    /**
     * The header lines of the diff. These lines are purely informational and are not parsed.
     *
     * @return the list of header lines.
     */
    public List<String> getHeaderLines() {
        return headerLines;
    }

    public void setHeaderLines(List<String> headerLines) {
        this.headerLines = headerLines;
    }

    /**
     * Gets the name of the first file that was compared with this Diff (the file "from" which the changes were made,
     * i.e. the "left" file of the diff).
     *
     * @return the name of the "from"-file.
     */
    public String getFromFileName() {
        return fromFileName;
    }

    /**
     * Gets the name of the second file that was compared with this Diff (the file "to" which the changes were made,
     * i.e. the "right" file of the diff).
     *
     * @return the name of the "to"-file.
     */
    public String getToFileName() {
        return toFileName;
    }

    /**
     * The list if all {@link org.wickedsource.diffparser.api.model.Hunk}s which contain all changes that are part of this Diff.
     *
     * @return list of all Hunks that are part of this Diff.
     */
    public List<Hunk> getHunks() {
        return hunks;
    }

    public void setFromFileName(String fromFileName) {
        this.fromFileName = fromFileName;
    }

    public void setToFileName(String toFileName) {
        this.toFileName = toFileName;
    }

    public void setHunks(List<Hunk> hunks) {
        this.hunks = hunks;
    }

    /**
     * Gets the last {@link org.wickedsource.diffparser.api.model.Hunk} of changes that is part of this Diff.
     *
     * @return the last {@link org.wickedsource.diffparser.api.model.Hunk} that has been added to this Diff.
     */
    public Hunk getLatestHunk() {
        return hunks.get(hunks.size() - 1);
    }
}
