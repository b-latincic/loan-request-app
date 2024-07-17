package com.fioneer.homework.converter.loanType;

import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.web.dto.loanType.UpdateLoanTypeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateLoanTypeDTOToLoanTypeConverter implements Converter<UpdateLoanTypeDTO, LoanType> {

    @Override
    public LoanType convert(UpdateLoanTypeDTO dto) {
        LoanType loanType = new LoanType();
        loanType.setName(dto.getName());

        return loanType;
    }
}
