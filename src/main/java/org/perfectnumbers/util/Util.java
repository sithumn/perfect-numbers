package org.perfectnumbers.util;

import java.util.*;

public class Util {

    private static Map<String, Long> powerOfTwo = new HashMap<>();

    /**
     * Generate Mersenne number of the form (2^p - 1)
     *
     * @param exponent - Exponent to be used to generate the number
     * @return long
     */
    public static long generateMersenneNumber(final int exponent) {
        if (exponent > 0 && exponent < 65) {
            return getPowerOfTwo(exponent) - 1;
        } else {
            return -1;
        }
    }

    /**
     * Primality check of a given number
     *
     * @param number - Number to be checked
     * @return true if the given number is a prime
     */
    public static boolean isPrime(final long number) {
        if (number == 2 || number == 3) return true;
        if (number < 2 || number % 2 == 0 || number % 3 == 0) return false;

        for(long i = 5; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of factors of a given number
     *
     * @param number - Number to be factorized
     * @return List<Long> with all the factors
     */
    public static List<Long> getFactors(final long number) {
        SortedSet <Long> factors = new TreeSet<>();
        if (number > 0) {
            for (long i = 1; i * i <= number; i++) {
                if (number % i == 0) {
                    factors.add(i);
                    factors.add(number / i);
                }
            }
        }

        return new ArrayList<>(factors);
    }

    public static long getPowerOfTwo(final int exponent) {
        if (exponent >= 0) {
            String expString = String.valueOf(exponent);

            if (powerOfTwo.containsKey(expString)) {
                return powerOfTwo.get(expString);
            } else {
                if (exponent == 0) {
                    powerOfTwo.put(String.valueOf(0), Long.valueOf(1));
                    return 1;
                } else {
                    long value = (long) Math.pow(2, exponent);
                    powerOfTwo.put(String.valueOf(exponent), Long.valueOf(value));
                    return value;
                }
            }
        } else {
            return -1;
        }
    }

}
