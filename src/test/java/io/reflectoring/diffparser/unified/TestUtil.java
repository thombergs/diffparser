package io.reflectoring.diffparser.unified;

import io.reflectoring.diffparser.api.model.Line;

import static org.testng.Assert.assertEquals;

class TestUtil {

    private TestUtil() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " only contains static utility methods " +
                "and should not be instantiated.");
    }

    static void assertLine(Line actualLine, Line.LineType expectedType, String expectedContent) {
        assertEquals(expectedType, actualLine.getLineType());
        assertEquals(expectedContent, actualLine.getContent());
    }
}
