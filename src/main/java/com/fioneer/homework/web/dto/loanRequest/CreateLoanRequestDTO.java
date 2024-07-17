package com.fioneer.homework.web.dto.loanRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateLoanRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Positive
    private double loanAmount;

    @NotBlank
    private String loanTypeId;
}
