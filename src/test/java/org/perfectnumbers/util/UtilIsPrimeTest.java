package org.perfectnumbers.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilIsPrimeTest {

    private long fNumber;
    private boolean fExpected;

    public UtilIsPrimeTest(long fNumber, boolean fExpected) {
        this.fNumber = fNumber;
        this.fExpected = fExpected;
    }

    @Parameterized.Parameters(name = "{index} : isPrime({0}) == {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { -7, false}, { 0, false}, { 1, false }, { 2, true }, { 3, true }, { 49, false } ,
                { 81, false }, { 13, true }, { 197, true }, {2305843009213693951L, true },
                {7, true}
        });
    }

    @Test
    public void testIsPrime() {
        assertEquals(NumberUtil.isPrime(fNumber), fExpected);
    }


}