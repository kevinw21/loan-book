package com.sullivankw.LoanBooks.models;

public class Facility {

    private float amount; // note the directions say it's an int but the file (small) has a xxx.0 value, so using float

    private float interestRate;

    private int facilityId;

    private int bankId;

    public Facility(float amount, float interestRate, int facilityId, int bankId) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.facilityId = facilityId;
        this.bankId = bankId;
    }

    public int getBankId() {
        return bankId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
