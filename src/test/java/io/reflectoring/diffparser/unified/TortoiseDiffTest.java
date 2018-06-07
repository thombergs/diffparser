package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import io.reflectoring.diffparser.api.model.Hunk;
import org.testng.annotations.Test;
import io.reflectoring.diffparser.api.model.Diff;
import io.reflectoring.diffparser.api.model.Line;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.reflectoring.diffparser.unified.TestUtil.assertLine;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests the {@link UnifiedDiffParser} with a diff created by Tortoise SVN.
 */
public class TortoiseDiffTest {

    @Test
    public void testParse() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        InputStream in = getClass().getResourceAsStream("tortoise.diff");

        // when
        List<Diff> diffs = parser.parse(in);

        // then
        assertNotNull(diffs);
        assertEquals(2, diffs.size());

        Diff diff1 = diffs.get(0);
        assertEquals("/trunk/test1 - Kopie (2).txt", diff1.getFromFileName());
        assertEquals("/trunk/test1 - Kopie (2).txt", diff1.getToFileName());
        assertEquals(2, diff1.getHunks().size());

        List<String> headerLines = diff1.getHeaderLines();
        assertEquals(2, headerLines.size());

        Hunk hunk1 = diff1.getHunks().get(0);
        assertEquals(1, hunk1.getFromFileRange().getLineStart());
        assertEquals(4, hunk1.getFromFileRange().getLineCount());
        assertEquals(1, hunk1.getToFileRange().getLineStart());
        assertEquals(3, hunk1.getToFileRange().getLineCount());

        List<Line> lines = hunk1.getLines();
        assertEquals(5, lines.size());
        assertLine(lines.get(0), Line.LineType.NEUTRAL, "test1");
        assertLine(lines.get(1), Line.LineType.FROM, "test1");
        assertLine(lines.get(2), Line.LineType.TO, "test234");
        assertLine(lines.get(3), Line.LineType.NEUTRAL, "");
        assertLine(lines.get(4), Line.LineType.FROM, "test1");
        assertTrue(diff1.isFromFileIncomplete());
        assertFalse(diff1.isToFileIncomplete());

        Diff diff2 = diffs.get(1);
        assertEquals(1, diff2.getHunks().size());
        assertEquals(8, diff2.getHunks().get(0).getLines().size());
        assertTrue(diff2.isFromFileIncomplete());
        assertTrue(diff2.isToFileIncomplete());
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
