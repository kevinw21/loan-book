package com.sullivankw.LoanBooks.models;

public class Loan {

    private float interestRate;

    private int amount;

    private int id;

    private float defaultLikelihood;

    private String state;

    public Loan(float interestRate, int amount, int id, float defaultLikelihood, String state) {
        this.interestRate = interestRate;
        this.amount = amount;
        this.id = id;
        this.defaultLikelihood = defaultLikelihood;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getDefaultLikelihood() {
        return defaultLikelihood;
    }

    public String getState() {
        return state;
    }
}
