package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanAssembler {

    public Loan from(String[] row) {
        return new Loan(getInterestRate(row), getAmount(row), getId(row), getDefaultLikelihood(row), getState(row));
    }

    private String getState(String[] row) {
        return row[4];
    }

    private float getDefaultLikelihood(String[] row) {
        return Float.valueOf(row[3]);
    }

    private int getId(String[] row) {
        return Integer.valueOf(row[2]);
    }

    private int getAmount(String[] row) {
        return Integer.valueOf(row[1]);
    }

    private float getInterestRate(String[] row) {
        return Float.valueOf(row[0]);
    }
}
