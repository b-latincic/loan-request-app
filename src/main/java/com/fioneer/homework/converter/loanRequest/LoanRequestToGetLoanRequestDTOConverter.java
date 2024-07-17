package com.fioneer.homework.converter.loanRequest;

import com.fioneer.homework.converter.requestStep.RequestStepToGetRequestStepDTOListConverter;
import com.fioneer.homework.model.LoanRequest;
import com.fioneer.homework.web.dto.loanRequest.GetLoanRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoanRequestToGetLoanRequestDTOConverter implements Converter<LoanRequest, GetLoanRequestDTO> {

    private final RequestStepToGetRequestStepDTOListConverter toListConverter;

    @Override
    public GetLoanRequestDTO convert(LoanRequest loanRequest) {
        GetLoanRequestDTO dto = new GetLoanRequestDTO();
        dto.setFirstName(loanRequest.getFirstName());
        dto.setLastName(loanRequest.getLastName());
        dto.setLoanAmount(loanRequest.getLoanAmount());
        dto.setRequestStatus(loanRequest.getRequestStatus());
        dto.setTotalExpectedTime(loanRequest.getTotalExpectedTime());
        dto.setTotalSpentTime(loanRequest.getTotalSpentTime());
        dto.setRequestSteps(toListConverter.convert(loanRequest.getRequestSteps()));
        dto.setLoanTypeId(loanRequest.getLoanType().getId());
        return dto;
    }
}
