package org.logtrace.pojo;

public class StartEndParamsPair {

    private Long start;
    private Long end;

    public StartEndParamsPair(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
