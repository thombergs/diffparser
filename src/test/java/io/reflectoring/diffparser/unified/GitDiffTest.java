/*
 * Copyright (c) Crosskey Banking Solutions. All rights reserved.
 */
package io.reflectoring.diffparser.unified;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.testng.annotations.Test;

import io.reflectoring.diffparser.api.DiffParser;
import io.reflectoring.diffparser.api.UnifiedDiffParser;
import io.reflectoring.diffparser.api.model.Diff;
import io.reflectoring.diffparser.api.model.Hunk;
import io.reflectoring.diffparser.api.model.Line;

public class GitDiffTest
{

  @Test
  public void testParse()
  {
    // given
    DiffParser parser = new UnifiedDiffParser();
    InputStream in = getClass().getResourceAsStream("git.diff");

    // when
    List<Diff> diffs = parser.parse(in);

    // then
    assertNotNull(diffs);
    assertEquals(diffs.size(), 3);

    Diff diff1 = diffs.get(0);
    assertEquals("a/diffparser/pom.xml", diff1.getFromFileName());
    assertEquals("b/diffparser/pom.xml", diff1.getToFileName());
    assertEquals(diff1.getHunks().size(), 2);

    List<String> headerLines = diff1.getHeaderLines();
    assertEquals(headerLines.size(), 2);

    Hunk hunk1 = diff1.getHunks().get(0);
    assertEquals(hunk1.getFromFileRange().getLineStart(), 6);
    assertEquals(hunk1.getFromFileRange().getLineCount(), 7);
    assertEquals(hunk1.getToFileRange().getLineStart(), 6);
    assertEquals(hunk1.getToFileRange().getLineCount(), 7);

    List<Line> lines = hunk1.getLines();
    assertEquals(lines.size(), 8);
    assertEquals(lines.get(3).getLineType(), Line.LineType.FROM);
    assertEquals(lines.get(4).getLineType(), Line.LineType.TO);
  }
}
