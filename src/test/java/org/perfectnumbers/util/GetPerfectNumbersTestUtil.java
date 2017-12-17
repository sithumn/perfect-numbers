package org.perfectnumbers.util;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.perfectnumbers.exception.NumberRangeException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class GetPerfectNumbersTestUtil {

    @RunWith(Parameterized.class)
    public static class WithValidParams {

        private long fStart;
        private long fEnd;
        private Long fContains;
        private long fSize;


        public WithValidParams(long fStart, long fEnd, Long fContains, long fSize) {
            this.fStart = fStart;
            this.fEnd = fEnd;
            this.fContains = fContains;
            this.fSize = fSize;
        }

        @Parameterized.Parameters(name = "{index} : (With valid params)getPerfectNumbersBetween({0}, {1}) contains {3} number(s). Max is {2}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 0, 10, new Long(6), 1},
                    { 0, 100, new Long(28), 2},
                    { 100, 1000, new Long(496), 1},
                    { -100, 100, new Long(28), 2},
                    { 0, 1374386913280L, new Long(137438691328L), 7 },
                    { 0, Long.MAX_VALUE, new Long(2305843008139952128L), 8},
            });
        }

        @Test
        public void testGetPerfectNumbersBetween() throws NumberRangeException {
            List<Long> actual = PerfectNumbers.getPerfectNumbersBetween(fStart, fEnd);
            assertEquals(fSize, actual.size());
            assertThat(actual, CoreMatchers.hasItem(fContains));
        }

    }

    @RunWith(Parameterized.class)
    public static class WithInvalidParams {
        private long fStart;
        private long fEnd;

        @Rule
        public ExpectedException thrown = ExpectedException.none();


        public WithInvalidParams(long fStart, long fEnd) {
            this.fStart = fStart;
            this.fEnd = fEnd;
        }

        @Parameterized.Parameters(name = "{index} : (With invalid params)getPerfectNumbersBetween({0}, {1})")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 0, Long.MAX_VALUE + 1},
                    { 0, -2000},
            });
        }

        @Test
        public void testGenerateMersenneNumber() throws NumberRangeException {
            thrown.expect(NumberRangeException.class);
            thrown.expectMessage(startsWith("Invalid number range "));

            PerfectNumbers.getPerfectNumbersBetween(fStart, fEnd);
        }
    }
}