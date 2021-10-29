package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankAssembler {

    public Bank from(String[] row) {
        return new Bank(getId(row), getName(row));
    }

    private Integer getId(String[] row) {
        return Integer.valueOf(row[0]);
    }

    private String getName(String[] row) {
        return row[1];
    }
}
