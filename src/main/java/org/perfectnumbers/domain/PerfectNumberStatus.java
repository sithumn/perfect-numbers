package org.perfectnumbers.domain;

public class PerfectNumberStatus {

    private long number;
    private boolean valid;

    public PerfectNumberStatus() {
    }

    public PerfectNumberStatus(long number, boolean valid) {
        this.number = number;
        this.valid = valid;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
