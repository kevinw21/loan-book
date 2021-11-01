package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Facility;
import com.sullivankw.LoanBooks.models.Loan;
import com.sullivankw.LoanBooks.models.Yield;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YieldAssembler {

    public List<Yield> from(List<Assignment> assignments, List<Loan> loans, List<Facility> facilities) {
        List<Yield> yields = new ArrayList<>();
        for (Assignment assignment : assignments) {
            Facility facility = facilities.stream().filter(f -> f.getFacilityId() == assignment.getFacilityId()).findFirst().get();
            Loan loan = loans.stream().filter(l -> l.getId() == assignment.getLoanId()).findFirst().get(); //wont really fail
            float expectedYield = (1 - loan.getDefaultLikelihood())
                    * loan.getInterestRate()
                    * loan.getAmount() - loan.getDefaultLikelihood()
                    * loan.getAmount() - facility.getInterestRate() * loan.getAmount();
            yields.add(new Yield(facility.getFacilityId(), expectedYield));
        }
        return yields;
    }
}
