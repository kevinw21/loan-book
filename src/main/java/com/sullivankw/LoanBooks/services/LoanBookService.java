package com.sullivankw.LoanBooks.services;

import com.sullivankw.LoanBooks.assemblers.BankAssembler;
import com.sullivankw.LoanBooks.assemblers.CovenantAssembler;
import com.sullivankw.LoanBooks.assemblers.FacilityAssembler;
import com.sullivankw.LoanBooks.assemblers.LoanAssembler;
import com.sullivankw.LoanBooks.models.Bank;
import com.sullivankw.LoanBooks.models.Covenant;
import com.sullivankw.LoanBooks.models.Facility;
import com.sullivankw.LoanBooks.models.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    private static final String CSV_FILE_SEPARATOR = ",";

    public List<Bank> getBanks() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:banks.csv");
        BufferedReader reader;
        String line = "";
        boolean readFile = true;
        int counter = 0;
        List<Bank> banks = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    if (!((line = reader.readLine()) != null))
                        readFile = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Bank bank = bankAssembler.from(row);
                    banks.add(bank);
                }
                counter++;
            }
        } catch (Exception e) {

        }
        return banks;
    }

    public List<Facility> getFacilities() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:facilities.csv");
        BufferedReader reader;
        String line = "";
        boolean readFile = true;
        int counter = 0;
        List<Facility> facilities = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    if (!((line = reader.readLine()) != null))
                        readFile = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Facility facility = facilityAssembler.from(row);
                    facilities.add(facility);
                }
                counter++;
            }
        } catch (Exception e) {
            //todo mapping from file to object errors here
            String hi = "hi";

        }
        return facilities;
    }

    public List<Covenant> getCovenants() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:covenants.csv");
        BufferedReader reader;
        String line = "";
        boolean readFile = true;
        int counter = 0;
        List<Covenant> covenants = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    if (!((line = reader.readLine()) != null))
                        readFile = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Covenant covenant = covenantAssembler.from(row);
                    covenants.add(covenant);
                }
                counter++;
            }
        } catch (Exception e) {
            //todo mapping from file to object errors here
            String hi = "hi";
        }
        return covenants;
    }

    public List<Loan> getLoans() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:loans.csv");
        BufferedReader reader;
        String line = "";
        boolean readFile = true;
        int counter = 0;
        List<Loan> loans = new ArrayList();
        try {
            reader = new BufferedReader(new FileReader(file));
            while (readFile) {
                try {
                    if (!((line = reader.readLine()) != null))
                        readFile = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (counter > 0) {
                    String[] row = line.split(CSV_FILE_SEPARATOR);
                    Loan loan = loanAssembler.from(row);
                    loans.add(loan);
                }
                counter++;
            }
        } catch (Exception e) {
            //todo mapping from file to object errors here
            String hi = "hi";
        }
        return loans;
    }
}
