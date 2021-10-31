package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Covenant;
import com.sullivankw.LoanBooks.models.Loan;
import org.springframework.stereotype.Component;

@Component
public class AssignmentAssembler {

    public Assignment from(Covenant covenant, Loan loan) {
        return new Assignment(loan.getId(), covenant.getFacilityId());
    }
}
