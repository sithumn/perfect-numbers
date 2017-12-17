package org.perfectnumbers.domain;

import java.util.ArrayList;
import java.util.List;

public class PerfectNumbersList {

    private List<Long> perfectNumbers = new ArrayList<>();


    public List<Long> getPerfectNumbers() {
        return perfectNumbers;
    }

    public void setPerfectNumbers(List<Long> perfectNumbers) {
        this.perfectNumbers = perfectNumbers;
    }
}
