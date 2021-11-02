package com.sullivankw.LoanBooks.dtos;

public class LoanProcessingRequestDto {

    private int id;

    private float interestRate;

    private int amount;

    private float defaultLikelihood;

    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getDefaultLikelihood() {
        return defaultLikelihood;
    }

    public void setDefaultLikelihood(float defaultLikelihood) {
        this.defaultLikelihood = defaultLikelihood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
