package org.logtrace.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IsPerfectNumberUtilTest {

    private long fNumber;
    private boolean fExpected;

    public IsPerfectNumberUtilTest(long fNumber, boolean fExpected) {
        this.fNumber = fNumber;
        this.fExpected = fExpected;
    }

    @Parameterized.Parameters(name = "{index} : isPerfectNumber({0}) == {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { -6, false}, { 0, false}, { 6, true}, { 10, false }, { 28, true }, { 8127, false }, { 8128, true } , { 33550336, true }
        });
    }

    @Test
    public void testIsPerfectNumber() {
        assertEquals(fExpected, PerfectNumbers.isPerfectNumber(fNumber));
    }
}