package com.sullivankw.LoanBooks.models;

public class Yield {

    private int facilityId;

    private double expectedYield; // double rounds better

    public Yield(int facilityId, double expectedYield) {
        this.facilityId = facilityId;
        this.expectedYield = expectedYield;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public double getExpectedYield() {
        return expectedYield;
    }
}
