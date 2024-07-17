package com.fioneer.homework.web.dto.loanRequest;

import com.fioneer.homework.enums.RequestStatus;
import com.fioneer.homework.web.dto.requestStep.GetRequestStepDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetLoanRequestDTO {

    private String id;
    private String firstName;
    private String lastName;
    private double loanAmount;
    private RequestStatus requestStatus;
    private int totalExpectedTime;
    private int totalSpentTime;
    private List<GetRequestStepDTO> requestSteps;
    private String loanTypeId;
}
