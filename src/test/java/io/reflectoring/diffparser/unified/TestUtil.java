package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.model.Line;
import junit.framework.Assert;

class TestUtil {

    private TestUtil() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " only contains static utility methods " +
                "and should not be instantiated.");
    }

    static void assertLine(Line actualLine, Line.LineType expectedType, String expectedContent) {
        Assert.assertEquals(expectedType, actualLine.getLineType());
        Assert.assertEquals(expectedContent, actualLine.getContent());
    }
}
