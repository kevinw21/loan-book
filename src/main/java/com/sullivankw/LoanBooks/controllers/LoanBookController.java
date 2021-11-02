package com.sullivankw.LoanBooks.controllers;

import com.sullivankw.LoanBooks.dtos.LoanBookResponseDto;
import com.sullivankw.LoanBooks.services.LoanBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/calculate")
public class LoanBookController {

    @Autowired
    private LoanBookService loanBookService;

    @GetMapping
    public LoanBookResponseDto getAssignmentsAndYields() throws IOException {
        return loanBookService.getAssignmentsAndYields();
    }
}
