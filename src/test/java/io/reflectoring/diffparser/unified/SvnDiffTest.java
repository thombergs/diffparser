package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import org.testng.annotations.Test;
import io.reflectoring.diffparser.api.model.Diff;
import io.reflectoring.diffparser.api.model.Hunk;
import io.reflectoring.diffparser.api.model.Line;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.reflectoring.diffparser.unified.TestUtil.assertLine;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests the {@link UnifiedDiffParser} with a diff created by the {@code svn diff} command.
 */
public class SvnDiffTest {

    @Test
    public void testParse() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        InputStream in = getClass().getResourceAsStream("svn.diff");

        // when
        List<Diff> diffs = parser.parse(in);

        // then
        assertNotNull(diffs);
        assertEquals(2, diffs.size());

        Diff diff1 = diffs.get(0);
        assertEquals("UnifiedDiffParser.java", diff1.getFromFileName());
        assertEquals("UnifiedDiffParser.java", diff1.getToFileName());
        assertEquals(1, diff1.getHunks().size());

        List<String> headerLines = diff1.getHeaderLines();
        assertEquals(2, headerLines.size());

        Hunk hunk1 = diff1.getHunks().get(0);
        assertEquals(73, hunk1.getFromFileRange().getLineStart());
        assertEquals(13, hunk1.getFromFileRange().getLineCount());
        assertEquals(73, hunk1.getToFileRange().getLineStart());
        assertEquals(13, hunk1.getToFileRange().getLineCount());

        List<Line> lines = hunk1.getLines();
        assertEquals(16, lines.size());
        assertLine(lines.get(2), Line.LineType.NEUTRAL, "                case TO_FILE:");
        assertLine(lines.get(3), Line.LineType.TO, "\t\t\t\t\tnew line");
        assertLine(lines.get(7), Line.LineType.FROM, "                    parseHunkStart(currentDiff, currentLine);");
        assertLine(lines.get(8), Line.LineType.TO, "                    changedLine(currentDiff, currentLine);");
    }

    @Test
    public void testParse_WhenHunkRangeLineCountNotSpecified_ShouldSetHunkRangeLineCountToOne() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        String in = ""
            + "--- from	2015-12-21 17:53:29.082877088 -0500\n"
            + "+++ to	2015-12-21 08:41:52.663714666 -0500\n"
            + "@@ -10 +10 @@\n"
            + "-from\n"
            + "+to\n"
            + "\n";

        // when
        List<Diff> diffs = parser.parse(in.getBytes(StandardCharsets.UTF_8));

        // then
        assertNotNull(diffs);
        assertEquals(1, diffs.size());

        Diff diff1 = diffs.get(0);
        assertEquals(1, diff1.getHunks().size());

        Hunk hunk1 = diff1.getHunks().get(0);
        assertEquals(1, hunk1.getFromFileRange().getLineCount());
        assertEquals(1, hunk1.getToFileRange().getLineCount());
    }

    @Test
    public void testParse_WhenInputDoesNotEndWithEmptyLine_ShouldTransitionToEndState() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        String in = ""
            + "--- from	2015-12-21 17:53:29.082877088 -0500\n"
            + "+++ to	2015-12-21 08:41:52.663714666 -0500\n"
            + "@@ -10,1 +10,1 @@\n"
            + "-from\n"
            + "+to\n";

        // when
        List<Diff> diffs = parser.parse(in.getBytes(StandardCharsets.UTF_8));

        // then
        assertNotNull(diffs);
        assertEquals(1, diffs.size());

        Diff diff1 = diffs.get(0);
        assertEquals(1, diff1.getHunks().size());
    }
}
