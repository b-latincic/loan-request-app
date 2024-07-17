package com.fioneer.homework.converter.loanRequest;

import com.fioneer.homework.model.LoanRequest;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.web.dto.loanRequest.CreateLoanRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateLoanRequestDTOToLoanRequestConverter implements Converter<CreateLoanRequestDTO, LoanRequest> {

    @Override
    public LoanRequest convert(CreateLoanRequestDTO dto) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setFirstName(dto.getFirstName());
        loanRequest.setLastName(dto.getLastName());
        loanRequest.setLoanAmount(dto.getLoanAmount());
        return loanRequest;
    }
}
