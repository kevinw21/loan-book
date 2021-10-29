package com.sullivankw.LoanBooks.controllers;

import com.sullivankw.LoanBooks.models.Bank;
import com.sullivankw.LoanBooks.models.Covenant;
import com.sullivankw.LoanBooks.models.Facility;
import com.sullivankw.LoanBooks.models.Loan;
import com.sullivankw.LoanBooks.services.LoanBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class LoanBookController {

    //todo temp entry to all files

    @Autowired
    private LoanBookService loanBookService;

    @GetMapping("/bank")
    public List<Bank> loadBanks() throws FileNotFoundException {
        return loanBookService.getBanks();
    }

    @GetMapping("/facility")
    public List<Facility> loadFacilities() throws FileNotFoundException {
        return loanBookService.getFacilities();
    }

    @GetMapping("/covenant")
    public List<Covenant> loadCovenants() throws FileNotFoundException {
        return loanBookService.getCovenants();
    }

    @GetMapping("/loan")
    public List<Loan> loans() throws FileNotFoundException {
        return loanBookService.getLoans();
    }
}
