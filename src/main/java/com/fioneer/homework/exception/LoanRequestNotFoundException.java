package com.fioneer.homework.exception;

public class LoanRequestNotFoundException extends RuntimeException {
    public LoanRequestNotFoundException(String id) {
        super(String.format("Loan request with ID %s not found.", id));
    }
}
