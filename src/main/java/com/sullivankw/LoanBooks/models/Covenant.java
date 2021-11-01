package com.sullivankw.LoanBooks.models;

public class Covenant {

    private int facilityId; // todo can be null apparently

    private Float maxDefaultLikelihood;

    private int bankId;

    private String bannedState;

    public Covenant(int facilityId, Float maxDefaultLikelihood, int bankId, String bannedState) {
        this.facilityId = facilityId;
        this.maxDefaultLikelihood = maxDefaultLikelihood;
        this.bankId = bankId;
        this.bannedState = bannedState;
    }

    public int getBankId() {
        return bankId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public Float getMaxDefaultLikelihood() {
        return maxDefaultLikelihood;
    }

    public String getBannedState() {
        return bannedState;
    }
}
