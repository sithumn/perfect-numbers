package org.perfectnumbers.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GenerateMersenneNumberUtilTest {

        private int fExponent;
        private long fExpected;


        public GenerateMersenneNumberUtilTest(int fExponent, long fExpected) {
            this.fExponent = fExponent;
            this.fExpected = fExpected;
        }

        @Parameterized.Parameters(name = "{index} : generateMersenneNumber({0}) == {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 1, 1}, { 11, 2047}, { 2, 3}, { 5, 31 }, { 61, 2305843009213693951L }, {0, -1}, {-1, -1}, {70, -1}
            });
        }

        @Test
        public void testGenerateMersenneNumber() {
            assertEquals(fExpected, NumberUtil.generateMersenneNumber(fExponent));
        }
}