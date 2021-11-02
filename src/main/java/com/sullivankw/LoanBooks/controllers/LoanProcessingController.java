package com.sullivankw.LoanBooks.controllers;


import com.sullivankw.LoanBooks.dtos.LoanProcessingRequestDto;
import com.sullivankw.LoanBooks.dtos.LoanProcessingResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/loan")
public class LoanProcessingController {

    @PostMapping
    @ApiOperation("Request the provided loan be assigned to a suitable facility")
    public LoanProcessingResponseDto processLoan(@RequestBody LoanProcessingRequestDto request) {
        return new LoanProcessingResponseDto(); // but actually process the loan against the internal list of facilities
    }
}
