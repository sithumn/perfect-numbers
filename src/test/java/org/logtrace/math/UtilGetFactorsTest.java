package org.logtrace.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilGetFactorsTest {

    private long fNumber;
    private List<Long> fExpected;

    public UtilGetFactorsTest(long number, List<Long> expected) {
        this.fNumber = number;
        this.fExpected = expected;
    }

    @Parameterized.Parameters(name = "{index} : getFactors({0}) == {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {-6, Arrays.asList()} ,
                {6, Arrays.asList(new Long(1), new Long(2), new Long(3), new Long(6))} ,
                {28, Arrays.asList(new Long(1), new Long(2), new Long(4), new Long(7), new Long(14), new Long(28))},
                {45, Arrays.asList(new Long(1), new Long(3), new Long(5), new Long(9), new Long(15), new Long(45))}
        });
    }


    @Test
    public void testGetFactors() {
        List<Long> actual =  Util.getFactors(fNumber);

        assertEquals(fExpected, actual);
    }

}
