package com.fioneer.homework.web.dto.loanType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateLoanTypeDTO {

    private String id;

    @NotBlank
    private String name;
}
