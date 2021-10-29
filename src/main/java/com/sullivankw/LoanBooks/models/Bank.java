package com.sullivankw.LoanBooks.models;

public class Bank {

    private int id;

    private String name;

    public Bank(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
