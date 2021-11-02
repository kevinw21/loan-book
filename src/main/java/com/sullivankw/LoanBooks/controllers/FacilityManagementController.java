package com.sullivankw.LoanBooks.controllers;

import com.sullivankw.LoanBooks.dtos.FacilityResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facility")
public class FacilityManagementController {

    @GetMapping("/{facilityId}")
    public FacilityResponseDto getFacility(@PathVariable int facilityId) {
        return new FacilityResponseDto(); // but really query the db or whatever the source of truth is
    }
}
