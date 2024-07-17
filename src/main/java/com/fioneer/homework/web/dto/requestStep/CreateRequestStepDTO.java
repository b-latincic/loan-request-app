package com.fioneer.homework.web.dto.requestStep;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRequestStepDTO {

    @NotBlank
    private String name;

    @Positive
    private int orderNo;

    @Positive
    private int expectedDuration;
}
