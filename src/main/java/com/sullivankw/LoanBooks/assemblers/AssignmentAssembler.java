package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Facility;
import com.sullivankw.LoanBooks.models.Loan;
import org.springframework.stereotype.Component;

@Component
public class AssignmentAssembler {

    public Assignment from(Loan loan, Facility facility) {
        return new Assignment(loan.getId(), facility.getFacilityId());
    }
}
