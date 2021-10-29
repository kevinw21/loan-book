package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityAssembler {

    public Facility from(String[] row) {
        return new Facility(getAmount(row), getInterestRate(row), getFacilityId(row), getBankId(row));
    }

    private int getBankId(String[] row) {
        return Integer.valueOf(row[3]);
    }

    private int getFacilityId(String[] row) {
        return Integer.valueOf(row[2]);
    }

    private float getInterestRate(String[] row) {
        return Float.valueOf(row[1]);
    }

    private float getAmount(String[] row) {
        return Float.valueOf(row[0]);
    }
}
