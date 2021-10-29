package com.sullivankw.LoanBooks.models;

public class Assignment {

    private int loanId;

    private int facilityId;

    public Assignment(int loanId, int facilityId) {
        this.loanId = loanId;
        this.facilityId = facilityId;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getFacilityId() {
        return facilityId;
    }
}
