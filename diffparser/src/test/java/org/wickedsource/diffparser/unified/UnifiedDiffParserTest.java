package org.wickedsource.diffparser.unified;

import junit.framework.Assert;
import org.testng.annotations.Test;
import org.wickedsource.diffparser.api.DiffParser;
import org.wickedsource.diffparser.api.UnifiedDiffParser;
import org.wickedsource.diffparser.api.model.Diff;
import org.wickedsource.diffparser.api.model.Hunk;
import org.wickedsource.diffparser.api.model.Line;

import java.io.InputStream;
import java.util.List;

public class UnifiedDiffParserTest {

    @Test
    public void testParse() throws Exception {
        // given
        DiffParser parser = new UnifiedDiffParser();
        InputStream in = getClass().getResourceAsStream("tortoise.diff");

        // when
        List<Diff> diffs = parser.parse(in);

        // then
        Assert.assertNotNull(diffs);
        Assert.assertEquals(2, diffs.size());

        Diff diff1 = diffs.get(0);
        Assert.assertEquals("/trunk/test1 - Kopie (2).txt", diff1.getFromFileName());
        Assert.assertEquals("/trunk/test1 - Kopie (2).txt", diff1.getToFileName());
        Assert.assertEquals(2, diff1.getHunks().size());

        List<String> headerLines = diff1.getHeaderLines();
        Assert.assertEquals(2, headerLines.size());

        Hunk hunk1 = diff1.getHunks().get(0);
        Assert.assertEquals(1, hunk1.getFromFileRange().getLineStart());
        Assert.assertEquals(4, hunk1.getFromFileRange().getLineEnd());
        Assert.assertEquals(1, hunk1.getToFileRange().getLineStart());
        Assert.assertEquals(3, hunk1.getToFileRange().getLineEnd());

        List<Line> lines = hunk1.getLines();
        Assert.assertEquals(6, lines.size());
        Assert.assertEquals(Line.LineType.NEUTRAL, lines.get(0).getLineType());
        Assert.assertEquals(Line.LineType.FROM, lines.get(1).getLineType());
        Assert.assertEquals(Line.LineType.TO, lines.get(2).getLineType());
        Assert.assertEquals(Line.LineType.NEUTRAL, lines.get(3).getLineType());
        Assert.assertEquals(Line.LineType.FROM, lines.get(4).getLineType());
        Assert.assertEquals(Line.LineType.NEUTRAL, lines.get(5).getLineType());

    }
}
