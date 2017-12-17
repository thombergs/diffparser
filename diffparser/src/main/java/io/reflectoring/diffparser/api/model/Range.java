/**
 *    Copyright 2013-2015 Tom Hombergs (tom.hombergs@gmail.com | http://wickedsource.org)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package io.reflectoring.diffparser.api.model;

/**
 * Represents a range of line numbers that spans a window on a text file.
 *
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 */
public class Range {

    private final int lineStart;

    private final int lineCount;

    public Range(int lineStart, int lineCount) {
        this.lineStart = lineStart;
        this.lineCount = lineCount;
    }

    /**
     * The line number at which this range starts (inclusive).
     *
     * @return the line number at which this range starts.
     */
    public int getLineStart() {
        return lineStart;
    }

    /**
     * The count of lines in this range.
     *
     * @return the count of lines in this range.
     */
    public int getLineCount() {
        return lineCount;
    }

}
