package org.logtrace.math;

import org.logtrace.exception.NumberRangeException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class PerfectNumber {

    /**
     * Checks if a given number is a perfect number
     *
     * @param number - Number to be checked
     * @return true if the number is a perfect number
     */
    public static boolean isPerfectNumber(final long number) {
        if (number < 2) return false;

        Util util = new Util();

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

            long mersennerNumber = Util.generateMersenneNumber(i);

            if (Util.isPrime(mersennerNumber)) {
                long perfectNumber = Util.getPowerOfTwo(i - 1) * mersennerNumber;
//                System.out.println(perfectNumber);

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
