package org.logtrace.math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.logtrace.exception.InvalidExponentException;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class GenerateMersenneNumberUtilTest {

    @RunWith(Parameterized.class)
    public static class WithValidParams {

        private int fExponent;
        private long fExpected;


        public WithValidParams(int fExponent, long fExpected) {
            this.fExponent = fExponent;
            this.fExpected = fExpected;
        }

        @Parameterized.Parameters(name = "{index} : (With valid params)generateMersenneNumber({0}) == {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 1, 1}, { 11, 2047}, { 2, 3}, { 5, 31 }, { 61, 2305843009213693951L }
            });
        }

        @Test
        public void testGenerateMersenneNumber() throws InvalidExponentException {
            assertEquals(fExpected, Util.generateMersenneNumber(fExponent));
        }

    }

    @RunWith(Parameterized.class)
    public static class WithInvalidParams {
        private int fExponent;

        @Rule
        public ExpectedException thrown = ExpectedException.none();


        public WithInvalidParams(int fExponent) {
            this.fExponent = fExponent;
        }

        @Parameterized.Parameters(name = "{index} : (With invalid params)generateMersenneNumber({0})")
        public static Object[] data() {
            return new Object[] { 0, -1, 70 };
        }

        @Test
        public void testGenerateMersenneNumber() throws InvalidExponentException {
            thrown.expect(InvalidExponentException.class);
            thrown.expectMessage(startsWith("Invalid exponent"));

            Util.generateMersenneNumber(fExponent);
        }
    }
}