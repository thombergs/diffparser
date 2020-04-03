/*
 * Copyright (c) Crosskey Banking Solutions. All rights reserved.
 */
package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import io.reflectoring.diffparser.api.model.Diff;
import io.reflectoring.diffparser.api.model.Hunk;
import io.reflectoring.diffparser.api.model.Line;
import io.reflectoring.diffparser.api.model.Range;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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

        List<Diff> expected = Arrays.asList(
                createDiff(Arrays.asList("diff --git a/diffparser/pom.xml b/diffparser/pom.xml",
                                         "index 5809534..4f4147a 100644"),
                           "a/diffparser/pom.xml",
                           "b/diffparser/pom.xml",
                           createHunk(new Range(6, 7),
                                      new Range(6, 7),
                                      new Line(Line.LineType.NEUTRAL, ""),
                                      new Line(Line.LineType.NEUTRAL, "    <groupId>org.wickedsource</groupId>"),
                                      new Line(Line.LineType.NEUTRAL, "    <artifactId>diffparser</artifactId>"),
                                      new Line(Line.LineType.FROM,    "    <version>1.1-SNAPSHOT</version>"),
                                      new Line(Line.LineType.TO,      "    <version>1.0</version>"),
                                      new Line(Line.LineType.NEUTRAL, "    <packaging>jar</packaging>"),
                                      new Line(Line.LineType.NEUTRAL, "    <name>diffparser</name>"),
                                      new Line(Line.LineType.NEUTRAL, "    <description>Parse textual diffs with Java.</description>")
                                     ),
                           createHunk(new Range(101, 19),
                                      new Range(101, 6),
                                      new Line(Line.LineType.NEUTRAL, "                    </execution>"),
                                      new Line(Line.LineType.NEUTRAL, "                </executions>"),
                                      new Line(Line.LineType.NEUTRAL, "            </plugin>"),
                                      new Line(Line.LineType.FROM,    "            <plugin>"),
                                      new Line(Line.LineType.FROM,    "                <groupId>org.apache.maven.plugins</groupId>"),
                                      new Line(Line.LineType.FROM,    "                <artifactId>maven-gpg-plugin</artifactId>"),
                                      new Line(Line.LineType.FROM,    "                <executions>"),
                                      new Line(Line.LineType.FROM,    "                    <execution>"),
                                      new Line(Line.LineType.FROM,    "                        <id>sign-artifacts</id>"),
                                      new Line(Line.LineType.FROM,    "                        <phase>verify</phase>"),
                                      new Line(Line.LineType.FROM,    "                        <goals>"),
                                      new Line(Line.LineType.FROM,    "                            <goal>sign</goal>"),
                                      new Line(Line.LineType.FROM,    "                        </goals>"),
                                      new Line(Line.LineType.FROM,    "                    </execution>"),
                                      new Line(Line.LineType.FROM,    "                </executions>"),
                                      new Line(Line.LineType.FROM,    "            </plugin>"),
                                      new Line(Line.LineType.NEUTRAL, "        </plugins>"),
                                      new Line(Line.LineType.NEUTRAL, "    </build>")//,
//                                              new Line(Line.LineType.NEUTRAL, "") //TODO: the last neutral line (empty, prefixes with a space) is interpreted as a separator between diffs, rather than as a neutral line
                                     )
                ),
                createDiff(Arrays.asList("diff --git a/diffparser/src/main/java/org/wickedsource/diffparser/api/UnifiedDiffParser.java b/diffparser/src/main/java/org/wickedsource/diffparser/api/UnifiedDiffParser.java",
                                         "index 0b53136..4dcdc64 100644"),
                           "a/diffparser/src/main/java/org/wickedsource/diffparser/api/UnifiedDiffParser.java",
                           "b/diffparser/src/main/java/org/wickedsource/diffparser/api/UnifiedDiffParser.java",
                           createHunk(new Range(79, 7),
                                      new Range(79, 7),
                                      new Line(Line.LineType.NEUTRAL, "                    parseHunkStart(currentDiff, currentLine);"),
                                      new Line(Line.LineType.NEUTRAL, "                    break;"),
                                      new Line(Line.LineType.NEUTRAL, "                case FROM_LINE:"),
                                      new Line(Line.LineType.FROM,    "                    parseFromLine(currentDiff, currentLine);"),
                                      new Line(Line.LineType.TO,      "                    parseFromLime(currentDiff, currentLine);"),
                                      new Line(Line.LineType.NEUTRAL, "                    break;"),
                                      new Line(Line.LineType.NEUTRAL, "                case TO_LINE:"),
                                      new Line(Line.LineType.NEUTRAL, "                    parseToLine(currentDiff, currentLine);")
                                     ),
                           createHunk(new Range(109, 7),
                                      new Range(109, 7),
                                      new Line(Line.LineType.NEUTRAL, "        currentDiff.getLatestHunk().getLines().add(toLine);"),
                                      new Line(Line.LineType.NEUTRAL, "    }"),
                                      new Line(Line.LineType.NEUTRAL, ""),
                                      new Line(Line.LineType.FROM,    "    private void parseFromLine(Diff currentDiff, String currentLine) {"),
                                      new Line(Line.LineType.TO,      "    private void parseFromLime(Diff currentDiff, String currentLine) {"),
                                      new Line(Line.LineType.NEUTRAL, "        Line fromLine = new Line(Line.LineType.FROM, currentLine.substring(1));"),
                                      new Line(Line.LineType.NEUTRAL, "        currentDiff.getLatestHunk().getLines().add(fromLine);"),
                                      new Line(Line.LineType.NEUTRAL, "    }")
                                     )
                ),
                createDiff(Arrays.asList("diff --git a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                                         "index d9f7e97..15c23a7 100644"),
                           "a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           "b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           createHunk(new Range(71, 6),
                                      new Range(71, 7),
                                      new Line(Line.LineType.NEUTRAL, "            for (int i = 0; i < numberOfLinesToLoad; i++) {"),
                                      new Line(Line.LineType.NEUTRAL, "                String nextLine = getNextLine();"),
                                      new Line(Line.LineType.NEUTRAL, "                if (nextLine != null) {"),
                                      new Line(Line.LineType.TO,      "                    nextLine = nextLine.trim();"),
                                      new Line(Line.LineType.NEUTRAL, "                    lineQueue.addLast(nextLine);"),
                                      new Line(Line.LineType.NEUTRAL, "                } else {"),
                                      new Line(Line.LineType.NEUTRAL, "                    throw new IndexOutOfBoundsException(\"End of stream has been reached!\");")
                                     ),
                           createHunk(new Range(89, 6),
                                      new Range(90, 7),
                                      new Line(Line.LineType.NEUTRAL, "            if (lineQueue.isEmpty()) {"),
                                      new Line(Line.LineType.NEUTRAL, "                String nextLine = getNextLine();"),
                                      new Line(Line.LineType.NEUTRAL, "                if (nextLine != null) {"),
                                      new Line(Line.LineType.TO,      "                    nextLine = nextLine.trim();"),
                                      new Line(Line.LineType.NEUTRAL, "                    lineQueue.addLast(nextLine);"),
                                      new Line(Line.LineType.NEUTRAL, "                }"),
                                      new Line(Line.LineType.NEUTRAL, "                return nextLine;")
                                     )
                ),
                createDiff(Arrays.asList("diff --git a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                                         "index d9f7e97..15c23a7 100644"),
                           "a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           "b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           createHunk(new Range(74, 0),
                                      new Range(74, 1),
                                      new Line(Line.LineType.TO,      "                    nextLine = nextLine.trim();")
                                     ),
                           createHunk(new Range(92, 0),
                                      new Range(93, 1),
                                      new Line(Line.LineType.TO,      "                    nextLine = nextLine.trim();")
                                     )
                ),
                createDiff(Arrays.asList("diff --git a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                                         "index d9f7e97..15c23a7 100644"),
                           "a/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           "b/diffparser/src/main/java/org/wickedsource/diffparser/unified/ResizingParseWindow.java",
                           createHunk(new Range(74, 1),
                                      new Range(74, 0),
                                      new Line(Line.LineType.FROM,    "                    nextLine = nextLine.trim();")
                                     ),
                           createHunk(new Range(92, 1),
                                      new Range(93, 0),
                                      new Line(Line.LineType.FROM,    "                    nextLine = nextLine.trim();")
                                     )
                )
        );

        assertDiffs(expected, diffs);
    }

    private static void assertLine(Line expected, Line actual) {
        assertEquals(expected.getLineType(), actual.getLineType());
        assertEquals(expected.getContent(), actual.getContent());
    }

    private static void assertRange(Range expected, Range actual) {
        assertEquals(expected.getLineStart(), actual.getLineStart());
        assertEquals(expected.getLineCount(), actual.getLineCount());
    }

    private static void assertHunk(Hunk expected, Hunk actual) {
        assertRange(expected.getFromFileRange(), actual.getFromFileRange());
        assertRange(expected.getToFileRange(), actual.getToFileRange());
        assertEquals(expected.getLines().size(), actual.getLines().size());
        for (int i = 0; i < expected.getLines().size(); i++) {
            assertLine(expected.getLines().get(i), actual.getLines().get(i));
        }
    }

    private static void assertDiff(Diff expected, Diff actual) {
        assertEquals(expected.getHeaderLines(), actual.getHeaderLines());
        assertEquals(expected.getFromFileName(), actual.getFromFileName());
        assertEquals(expected.getToFileName(), actual.getToFileName());
        assertEquals(expected.getHunks().size(), actual.getHunks().size());
        for (int i = 0; i < expected.getHunks().size(); i++) {
            System.err.println("checking hunk: " + i);
            assertHunk(expected.getHunks().get(i), actual.getHunks().get(i));
        }
    }

    private static void assertDiffs(List<Diff> expected, List<Diff> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            System.err.println("checking diff: " + i);
            assertDiff(expected.get(i), actual.get(i));
        }
    }

    private static Hunk createHunk(Range from, Range to, Line... lines) {
        Hunk h = new Hunk();
        h.setFromFileRange(from);
        h.setToFileRange(to);
        h.setLines(Collections.unmodifiableList(Arrays.asList(lines)));
        return h;
    }

    private static Diff createDiff(List<String> headerLines, String fromFile, String toFile, Hunk... hunks) {
        Diff d = new Diff();
        d.setHeaderLines(headerLines);
        d.setFromFileName(fromFile);
        d.setToFileName(toFile);
        d.setHunks(Collections.unmodifiableList(Arrays.asList(hunks)));
        return d;
    }
}
