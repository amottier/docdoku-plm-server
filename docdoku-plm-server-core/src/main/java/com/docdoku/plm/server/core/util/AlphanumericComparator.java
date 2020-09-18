/*
 * DocDoku, Professional Open Source
 * Copyright 2006 - 2020 DocDoku SARL
 *
 * This file is part of DocDokuPLM.
 *
 * DocDokuPLM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDokuPLM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with DocDokuPLM.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.docdoku.plm.server.core.util;

import java.text.Collator;
import java.util.Comparator;

/**
 * Comparator for ordering by Collator while treating digits numerically.
 * This provides a "natural" order that humans usually perceive as 'logical'.
 *
 * It should work reasonably well for western languages (provided you
 * use the proper collator when constructing). For free control over the
 * Collator, use the constructor that takes a Collator as parameter.
 * Configure the Collator using Collator.setDecomposition()/setStrength()
 * to suit your requirements.
 *
 * Originally from: http://stackoverflow.com/questions/12640280/looking-for-a-combination-of-alphabetical-and-natural-order-aka-user-sane-sort
 * 2014-12-01 by Konstantin Petrukhnov
 *
 */
public class AlphanumericComparator implements Comparator<CharSequence> {

    /**
     * The collator used for comparison of the alpha part.
     */
    private final Collator collator;

    /**
     * Creates comparator using platform default collator.
     * (equivalent to using Collator.getInstance())
     */
    public AlphanumericComparator() {
        this(Collator.getInstance());
    }

    /**
     * Creates comparator using specified collator.
     */
    public AlphanumericComparator(final Collator collator) {
        if (collator == null) {
            throw new IllegalArgumentException("collator must not be null");
        }
        this.collator = collator;
    }

    /**
     * Ideally this would be generalized to Character.isDigit(), but I have
     * no knowledge about arabic language and other digits, so I treat
     * them as characters...
     */
    private static boolean isDigit(final int character) {
        // code between ASCII '0' and '9'?
        return character >= 48 && character <= 57;
    }

    /**
     * Gets sub-sequence of only characters or only digits, but not mixed
     *
     * Fixed issue when last chars are numbers, and strings have different length.  E.g. "abc10" and "abc2"
     */
    private static CharSequence getChunk(final CharSequence charSeq, final int start) {
        int index = start;
        final int length = charSeq.length();
        final boolean mode = isDigit(charSeq.charAt(index++));
        while (index < length) {
            if (isDigit(charSeq.charAt(index)) != mode) {
                break;
            } else {
                index++;
            }
        }
        return charSeq.subSequence(start, index);
    }

    /**
     * Implements Comparator<CharSequence>.compare
     */
    public int compare(final CharSequence charSeq1, final CharSequence charSeq2) {
        final int length1 = charSeq1.length();
        final int length2 = charSeq2.length();
        int index1 = 0;
        int index2 = 0;
        int result = 0;
        while (result == 0 && index1 < length1 && index2 < length2) {
            final CharSequence chunk1 = getChunk(charSeq1, index1);
            index1 += chunk1.length();

            final CharSequence chunk2 = getChunk(charSeq2, index2);
            index2 += chunk2.length();

            if (isDigit(chunk1.charAt(0)) && isDigit(chunk2.charAt(0))) {
                final int clen1 = chunk1.length();
                final int clen2 = chunk2.length();
                // counts and skips leading zeros
                int zeros1 = 0;
                while (zeros1 < clen1 && chunk1.charAt(zeros1) == '0') {
                    ++zeros1;
                }
                // counts and skips leading zeros
                int zeros2 = 0;
                while (zeros2 < clen2 && chunk2.charAt(zeros2) == '0') {
                    ++zeros2;
                }
                // the longer run of non-zero digits is greater
                result = (clen1 - zeros1) - (clen2 - zeros2);
                // if the length is the same, the first differing digit decides
                // which one is deemed greater.
                int subi1 = zeros1;
                int subi2 = zeros2;
                while (result == 0 && subi1 < clen1 && subi2 < clen2) {
                    result = chunk1.charAt(subi1++) - chunk2.charAt(subi2++);
                }
                // if still no difference, the longer zeros-prefix is greater
                if (result == 0)
                    result = subi1 - subi2;
            } else {
                // in case we are working with Strings, toString() doesn't create
                // any objects (String.toString() returns the same string itself).
                result = collator.compare(chunk1.toString(), chunk2.toString());
            }
        }
        // if there was no difference at all, lets the longer one be the greater one
        if (result == 0) {
            result = length1 - length2;
        }
        // limits result to (-1, 0, or 1)
        return Integer.signum(result);
    }


}
