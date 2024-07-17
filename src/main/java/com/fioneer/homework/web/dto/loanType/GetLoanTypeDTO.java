package com.fioneer.homework.web.dto.loanType;

import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GetLoanTypeDTO {

    private String id;
    private String name;
    private List<GetProcessingStepDTO> processingSteps;
    private int totalDuration;

}
