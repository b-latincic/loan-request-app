package com.fioneer.homework.converter.loanType;

import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.web.dto.loanType.CreateLoanTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateLoanTypeDTOToLoanTypeConverter implements Converter<CreateLoanTypeDTO, LoanType> {
    @Override
    public LoanType convert(CreateLoanTypeDTO dto) {
        LoanType loanType = new LoanType();
        loanType.setName(dto.getName());

        return loanType;
    }
}
