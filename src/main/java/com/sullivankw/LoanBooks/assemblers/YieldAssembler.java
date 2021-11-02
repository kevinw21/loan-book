package com.sullivankw.LoanBooks.assemblers;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Facility;
import com.sullivankw.LoanBooks.models.Loan;
import com.sullivankw.LoanBooks.models.Yield;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class YieldAssembler {

    public List<Yield> from(List<Assignment> assignments, List<Loan> loans, List<Facility> facilities) {
        List<Yield> yields = new ArrayList<>();

        for (Assignment assignment : assignments) {
            Facility facility = facilities.stream()
                    .filter(f -> f.getFacilityId() == assignment.getFacilityId())
                    .findFirst().get(); //wont really fail

            Loan loan = loans.stream()
                    .filter(l -> l.getId() == assignment.getLoanId())
                    .findFirst().get(); //wont really fail

            double expectedYield = getExpectedYield(facility, loan);

            yields.add(new Yield(facility.getFacilityId(), expectedYield));
        }

        return yields;
    }

    public List<Yield> group(List<Yield> yields) {
        Map<Integer, List<Yield>> groupedMap =
                yields.stream()
                        .collect(groupingBy(Yield::getFacilityId));

        List<Yield> groupedYields = new ArrayList<>();

        for (Map.Entry<Integer, List<Yield>> set : groupedMap.entrySet()) {
            double sum = set.getValue().stream()
                    .mapToDouble(Yield::getExpectedYield)
                    .sum();

            double roundedUp = Math.ceil(sum);

            Yield yield = new Yield(set.getKey(), (int) roundedUp);

            groupedYields.add(yield);
        }

        return groupedYields;
    }


    private double getExpectedYield(Facility facility, Loan loan) {
        return (double) ((1 - loan.getDefaultLikelihood())
                * loan.getInterestRate()
                * loan.getAmount() - loan.getDefaultLikelihood()
                * loan.getAmount() - facility.getInterestRate() * loan.getAmount());
    }
}