package com.fioneer.homework.web.dto.loanType;

import com.fioneer.homework.model.ProcessingStep;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateLoanTypeDTO {

    @NotBlank(message = "Name must not be blank!")
    private String name;

    private List<String> stepIds;


}
