package com.sullivankw.LoanBooks.dtos;

import com.sullivankw.LoanBooks.models.Assignment;
import com.sullivankw.LoanBooks.models.Yield;

import java.util.List;

public class LoanBookResponseDto {

    private List<Assignment> assignments;

    private List<Yield> yields;

    public LoanBookResponseDto(List<Assignment> assignments, List<Yield> yields) {
        this.assignments = assignments;
        this.yields = yields;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Yield> getYields() {
        return yields;
    }
}
