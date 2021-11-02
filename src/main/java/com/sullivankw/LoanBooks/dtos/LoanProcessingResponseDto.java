package com.sullivankw.LoanBooks.dtos;

public class LoanProcessingResponseDto {

    private int id;

    private LoanStatus loanStatus;

    //other stuff if ya want it


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
