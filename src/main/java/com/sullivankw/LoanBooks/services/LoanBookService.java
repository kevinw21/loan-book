package com.sullivankw.LoanBooks.services;

import com.sullivankw.LoanBooks.assemblers.*;
import com.sullivankw.LoanBooks.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LoanBookService {

    @Autowired
    private BankAssembler bankAssembler;

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

    private static final String CSV_FILE_SEPARATOR = ",";

    public List<Assignment> calculateLoans() throws FileNotFoundException {

        List<Covenant> covenants = getCovenants();
        List<Loan> loans = getLoans();
        List<Assignment> assignments = new ArrayList<>();
        for (Loan loan : loans) {
            Covenant covenant = findAndRemoveMatchingCovenant(loan, covenants); //todo gotta remove and make sure no duplicate
            Assignment assignment = assignmentAssembler.from(covenant, loan);
            assignments.add(assignment);
            //todo still need to remove the covenant
        }
        writerService.generateAssignmentCsvFile(assignments);
        return assignments;
    }

    private Covenant findAndRemoveMatchingCovenant(Loan loan, List<Covenant> covenants) {
        return covenants.stream().filter(covenant -> covenant.getBannedState() != loan.getState()
                && covenant.getMaxDefaultLikelihood() >= loan.getDefaultLikelihood()).findFirst().orElse(null);
    }

    public List<Bank> getBanks() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:banks.csv");
        BufferedReader reader;
        String line;
        boolean readFile = true;
        int counter = 0;
        List<Bank> banks = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    line = reader.readLine();
                    if (Objects.isNull(line) || line.equals("")) {
                        readFile = false;
                    } else {
                        if (counter > 0) {
                            String[] row = line.split(CSV_FILE_SEPARATOR);
                            Bank bank = bankAssembler.from(row);
                            banks.add(bank);
                        }
                        counter++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getBanks IOEXCEPTION");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("getBanks");
        }
        return banks;
    }

    public List<Facility> getFacilities() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:facilities.csv");
        BufferedReader reader;
        String line;
        boolean readFile = true;
        int counter = 0; // don't wanna read the first line
        List<Facility> facilities = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    line = reader.readLine();
                    if (Objects.isNull(line) || line.equals("")) {
                        readFile = false;
                    } else {
                        if (counter > 0) {
                            String[] row = line.split(CSV_FILE_SEPARATOR);
                            Facility facility = facilityAssembler.from(row);
                            facilities.add(facility);
                        }
                        counter++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getFacility IOEXCEPTION");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("getFacility");
        }
        return facilities;
    }

    public List<Covenant> getCovenants() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:covenants.csv");
        BufferedReader reader;
        String line;
        boolean readFile = true;
        int counter = 0;
        List<Covenant> covenants = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    line = reader.readLine();
                    if (Objects.isNull(line) || line.equals("")) {
                        readFile = false;
                    } else {
                        if (counter > 0) {
                            String[] row = line.split(CSV_FILE_SEPARATOR);
                            Covenant covenant = covenantAssembler.from(row);
                            covenants.add(covenant);
                        }
                        counter++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getCovenants IOEXCEPTION");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("getCovenants");
        }
        return covenants;
    }

    public List<Loan> getLoans() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:loans.csv");
        BufferedReader reader;
        String line;
        boolean readFile = true;
        int counter = 0;
        List<Loan> loans = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    line = reader.readLine();
                    if (Objects.isNull(line) || line.equals("")) {
                        readFile = false;
                    } else {
                        if (counter > 0) {
                            String[] row = line.split(CSV_FILE_SEPARATOR);
                            Loan loan = loanAssembler.from(row);
                            loans.add(loan);
                        }
                        counter++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getLoans IOEXCEPTION");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("getLoans");
        }
        return loans;
    }
}
