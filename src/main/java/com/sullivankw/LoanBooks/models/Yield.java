package com.sullivankw.LoanBooks.models;

public class Yield {

    private int facilityId;

    private float expectedYield; //float seems best to me it rounds better

    public Yield(int facilityId, float expectedYield) {
        this.facilityId = facilityId;
        int hi = (int) expectedYield; //todo do round until you've grouped all facilities and added, then you can convert to int
        this.expectedYield = expectedYield;

    }

    public float getFacilityId() {
        return facilityId;
    }

    public float getExpectedYield() {
        return expectedYield;
    }
}
