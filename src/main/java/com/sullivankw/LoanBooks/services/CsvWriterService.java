package com.sullivankw.LoanBooks.services;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Yield;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CsvWriterService {

    public void generateAssignmentCsvFile(List<Assignment> assignments) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("assignments.csv")) {

            StringBuilder sb = new StringBuilder();

            write(sb, "loan_id", "facility_id");

            assignments.forEach(assignment -> write(sb, assignment.getLoanId(), assignment.getFacilityId()));

            writer.write(sb.toString());
        }
    }

    public void generateYieldCsvFile(List<Yield> yields) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("assignments.csv")) {

            StringBuilder sb = new StringBuilder();

            write(sb, "facility_id", "expected_yield");

            yields.forEach(yield -> write(sb, yield.getFacilityId(), yield.getExpectedYield()));

            writer.write(sb.toString());
        }
    }

    private void write(StringBuilder sb, Object value1, Object value2) {
        sb.append(value1);
        sb.append(',');
        sb.append(value2);
        sb.append('\n');
    }
}
