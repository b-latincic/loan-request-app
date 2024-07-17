package com.fioneer.homework.web.dto.processingStep;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProcessingStepDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Positive
    private int orderNo;

    @NotBlank
    private int expectedDuration;

    @NotBlank
    private String loanTypeId;
}
