package org.perfectnumbers.util;

import org.perfectnumbers.exception.NumberRangeException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PerfectNumbers {

    /**
     * Checks if a given number is a perfect number
     *
     * @param number - Number to be checked
     * @return true if the number is a perfect number
     */
    public static boolean isPerfectNumber(final long number) {
        if (number < 2) return false;

        NumberUtil util = new NumberUtil();

        if (number > 1) {
            List<Long> factors = util.getFactors(number);

            long factorSum = factors.stream().mapToLong(factor -> factor).sum();
            if (number == factorSum / 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of perfect numbers within the given range
     *
     * @param start - Starting number of the range
     * @param end - Ending number of the range
     * @return list of perfect numbers
     *
     * @throws NumberRangeException
     */
    public static List<Long> getPerfectNumbersBetween(final long start, final long end) throws NumberRangeException {
        if (end < 0) {
            throw new NumberRangeException(MessageFormat.format("Invalid number range {0} - {1}", start, end));
        }

        List<Long> perfectNumbers = new ArrayList<>();

        for(int i = 1;; i++) {

            long mersennerNumber = NumberUtil.generateMersenneNumber(i);

            if (NumberUtil.isPrime(mersennerNumber)) {
                long perfectNumber = NumberUtil.getPowerOfTwo(i - 1) * mersennerNumber;

                if (perfectNumber > start && perfectNumber < end) {
                    perfectNumbers.add(perfectNumber);
                }
                if (perfectNumber > end) {
                    break;
                }
            } else if (mersennerNumber < 0) {
                break;
            }

        }

        return perfectNumbers;
    }

}
