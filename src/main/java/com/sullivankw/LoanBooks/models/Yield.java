package com.sullivankw.LoanBooks.models;

public class Yield {

    private int facilityId;

    private int expectedYield;

    public Yield(int facilityId, int expectedYield) {
        this.facilityId = facilityId;
        this.expectedYield = expectedYield;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public int getExpectedYield() {
        return expectedYield;
    }
}
