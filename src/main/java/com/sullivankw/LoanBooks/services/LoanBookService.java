package com.sullivankw.LoanBooks.services;

import com.sullivankw.LoanBooks.assemblers.*;
import com.sullivankw.LoanBooks.dtos.LoanBookResponseDto;
import com.sullivankw.LoanBooks.models.*;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class LoanBookService {

    @Autowired
    private FacilityAssembler facilityAssembler;

    @Autowired
    private CovenantAssembler covenantAssembler;

    @Autowired
    private LoanAssembler loanAssembler;

    @Autowired
    private AssignmentAssembler assignmentAssembler;

    @Autowired
    private CsvWriterService writerService;

    @Autowired
    private YieldAssembler yieldAssembler;

    private static final String CSV_FILE_SEPARATOR = ",";

    private static final Logger LOGGER = LogManager.getLogger(LoanBookService.class);

    public LoanBookResponseDto getAssignmentsAndYields() throws IOException {
        List<Covenant> covenants = getCovenants();

        List<Loan> loans = getLoans();

        List<Facility> facilities = getFacilities();

        List<Assignment> assignments = new ArrayList<>();

        for (Loan loan : loans) {
            Facility facility = getBestRateFacilityRemaining(facilities, covenants, loan);
            if (nonNull(facility)) {
                Assignment assignment = assignmentAssembler.from(loan, facility);
                assignments.add(assignment);
            } else {
                LOGGER.info("Unable to fund loan %s", loan.getId());
            }
        }

        writerService.generateAssignmentCsvFile(assignments);

        List<Yield> yields = yieldAssembler.from(assignments, loans, facilities);

        List<Yield> groupedYields = yieldAssembler.group(yields);

        writerService.generateYieldCsvFile(groupedYields);

        return new LoanBookResponseDto(assignments, groupedYields);
    }

    private boolean canFundLoan(Facility facility, List<Covenant> covenants, Loan loan) {
        List<Covenant> relevantCovenants = getBestRateCovenants(facility, covenants);

        boolean loanIsFromBannedState = relevantCovenants.stream()
                .anyMatch(covenant -> loan.getState().equals(covenant.getBannedState()));

        boolean loanIsOverMaxAllowedLimit = relevantCovenants.stream()
                .filter(covenant -> covenant.getMaxDefaultLikelihood() != null)
                .anyMatch(d -> loan.getDefaultLikelihood() > d.getMaxDefaultLikelihood());

        boolean insufficientFunds = facility.getAmount() < loan.getAmount();

        return !loanIsFromBannedState && !loanIsOverMaxAllowedLimit && !insufficientFunds;
    }


    private List<Covenant> getBestRateCovenants(Facility bestRateFacility, List<Covenant> covenants) {
        return covenants.stream()
                .filter(cov -> bestRateFacility.getFacilityId() == cov.getFacilityId())
                .collect(Collectors.toList());
    }

    private Facility getLowestRateFacility(List<Facility> facilities) {
        return facilities
                .stream()
                .min(Comparator.comparing(Facility::getInterestRate))
                .orElseThrow(NoSuchElementException::new);
    }

    private Facility getBestRateFacilityRemaining(List<Facility> facilities, List<Covenant> covenants, Loan loan) {
        Facility facility = getLowestRateFacility(facilities);

        boolean canFundLoan = canFundLoan(facility, covenants, loan);

        if (canFundLoan) {
            updateFacilityRemainingAmount(loan, facility);
            return facility;
        } else {
            if (facilities.size() > 1) {
                List<Facility> copy = getCopy(facilities);
                copy.remove(facility); //this facility cannot support the loan
                return getBestRateFacilityRemaining(copy, covenants, loan);
            }
        }
        return null; //no facility can support loan
    }

    private void updateFacilityRemainingAmount(Loan loan, Facility facility) {
        facility.setAmount(facility.getAmount() - loan.getAmount());
    }

    private List<Facility> getFacilities() throws IOException {
        File file = ResourceUtils.getFile("classpath:facilities.csv");

        String line;

        boolean readFile = true;

        int counter = 0; // don't wanna read the first line

        List<Facility> facilities = new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (readFile) {

            line = reader.readLine();

            if (isNull(line) || line.equals("")) {
                readFile = false;
            } else {
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Facility facility = facilityAssembler.from(row);
                    facilities.add(facility);
                }
                counter++;
            }
        }

        reader.close();

        return facilities;
    }

    private List<Covenant> getCovenants() throws IOException {
        File file = ResourceUtils.getFile("classpath:covenants.csv");

        String line;

        boolean readFile = true;

        int counter = 0;

        List<Covenant> covenants = new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while (readFile) {

            line = reader.readLine();

            if (isNull(line) || line.equals("")) {
                readFile = false;
            } else {
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Covenant covenant = covenantAssembler.from(row);
                    covenants.add(covenant);
                }
                counter++;
            }
        }

        reader.close();

        return covenants;
    }

    private List<Loan> getLoans() throws IOException {
        File file = ResourceUtils.getFile("classpath:loans.csv");

        String line;

        boolean readFile = true;

        int counter = 0;

        List<Loan> loans = new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while (readFile) {

            line = reader.readLine();

            if (isNull(line) || line.equals("")) {
                readFile = false;
            } else {
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Loan loan = loanAssembler.from(row);
                    loans.add(loan);
                }
                counter++;
            }
        }

        reader.close();

        return loans;
    }

    private List<Facility> getCopy(List<Facility> facilities) {
        return facilities.stream()
                .collect(Collectors.toList());
    }
}
