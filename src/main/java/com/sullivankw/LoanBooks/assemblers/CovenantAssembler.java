package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Covenant;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CovenantAssembler {

    public Covenant from(String[] row) {
        return new Covenant(getFacilityId(row), getMaxDefaultLikelihood(row), getBankId(row), getBannedState(row));
    }

    private String getBannedState(String[] row) {
        return row[3];
    }

    private int getBankId(String[] row) {
        return Integer.valueOf(row[2]);
    }

    private Float getMaxDefaultLikelihood(String[] row) {
        String value = row[1];
        if (Objects.isNull(value) || value.equals("")) {
            return null;
        }
        return Float.valueOf(value);
    }

    private int getFacilityId(String[] row) {
        return Integer.valueOf(row[0]);
    }
}
