/*
 * Copyright (c) Crosskey Banking Solutions. All rights reserved.
 */
package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import io.reflectoring.diffparser.api.model.Diff;
import io.reflectoring.diffparser.api.model.Hunk;
import io.reflectoring.diffparser.api.model.Line;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.testng.annotations.Test;

import static io.reflectoring.diffparser.unified.TestUtil.assertLine;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Tests the {@link UnifiedDiffParser} with a diff created by the {@code git diff} command.
 */
public class GitDiffTest {

    @Test
    public void testParse() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        InputStream in = getClass().getResourceAsStream("git.diff");

        // when
        List<Diff> diffs = parser.parse(in);

        // then
        assertNotNull(diffs);
        assertEquals(3, diffs.size());

        Diff diff1 = diffs.get(0);
        assertEquals("a/diffparser/pom.xml", diff1.getFromFileName());
        assertEquals("b/diffparser/pom.xml", diff1.getToFileName());
        assertEquals(2, diff1.getHunks().size());

        List<String> headerLines = diff1.getHeaderLines();
        assertEquals(2, headerLines.size());

        Hunk hunk1 = diff1.getHunks().get(0);
        assertEquals(6, hunk1.getFromFileRange().getLineStart());
        assertEquals(7, hunk1.getFromFileRange().getLineCount());
        assertEquals(6, hunk1.getToFileRange().getLineStart());
        assertEquals(7, hunk1.getToFileRange().getLineCount());

        List<Line> lines = hunk1.getLines();
        assertEquals(8, lines.size());
        assertLine(lines.get(2), Line.LineType.NEUTRAL, "    <artifactId>diffparser</artifactId>");
        assertLine(lines.get(3), Line.LineType.FROM, "    <version>1.1-SNAPSHOT</version>");
        assertLine(lines.get(4), Line.LineType.TO, "    <version>1.0</version>");
    }

    @Test
    public void testParse_IncompleteLinesInTheFirstFile() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        String in = "" +
                "diff --git a/test.txt b/test.txt\n" +
                "index 2e65efe..7898192 100644\n" +
                "--- a/test.txt\n" +
                "+++ b/test.txt\n" +
                "@@ -1 +1 @@\n" +
                "-a\n" +
                "\\ No newline at end of file\n" +
                "+a";

        // when
        List<Diff> diffs = parser.parse(in.getBytes(StandardCharsets.UTF_8));

        // then
        assertNotNull(diffs);
        assertEquals(1, diffs.size());

        Diff diff = diffs.get(0);
        assertEquals(1, diff.getHunks().size());

        List<Line> lines = diff.getHunks().get(0).getLines();
        assertEquals(2, lines.size());
        assertLine(lines.get(0), Line.LineType.FROM, "a");
        assertLine(lines.get(1), Line.LineType.TO, "a");
        assertTrue(diff.isFromFileIncomplete());
        assertFalse(diff.isToFileIncomplete());
    }

    @Test
    public void testParse_IncompleteLinesInTheSecondFile() {
        // given
        DiffParser parser = new UnifiedDiffParser();
        String in = "" +
                "diff --git a/test.txt b/test.txt\n" +
                "index 7898192..2e65efe 100644\n" +
                "--- a/test.txt\n" +
                "+++ b/test.txt\n" +
                "@@ -1 +1 @@\n" +
                "-a\n" +
                "+a\n" +
                "\\ No newline at end of file";

        // when
        List<Diff> diffs = parser.parse(in.getBytes(StandardCharsets.UTF_8));

        // then
        assertNotNull(diffs);
        assertEquals(1, diffs.size());

        Diff diff = diffs.get(0);
        assertEquals(1, diff.getHunks().size());

        List<Line> lines = diff.getHunks().get(0).getLines();
        assertEquals(2, lines.size());
        assertLine(lines.get(0), Line.LineType.FROM, "a");
        assertLine(lines.get(1), Line.LineType.TO, "a");
        assertFalse(diff.isFromFileIncomplete());
        assertTrue(diff.isToFileIncomplete());
    }
}
