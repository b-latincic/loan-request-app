package com.fioneer.homework.exception;

public class LoanTypeNotFoundException extends RuntimeException {
    public LoanTypeNotFoundException(String id) {
        super(String.format("Loan type with ID %s not found.", id));
    }

}
