package com.fioneer.homework.converter.loanType;

import com.fioneer.homework.converter.processingStep.ProcessingStepToGetProcessingStepDTOListConverter;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.web.dto.loanType.GetLoanTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoanTypeToGetLoanTypeDTOConverter implements Converter<LoanType, GetLoanTypeDTO> {

    private final ProcessingStepToGetProcessingStepDTOListConverter processingStepToGetProcessingStepDTOListConverter;

    @Override
    public GetLoanTypeDTO convert(LoanType loanType) {
        GetLoanTypeDTO dto = new GetLoanTypeDTO();
        dto.setId(loanType.getId());
        dto.setName(loanType.getName());
        dto.setTotalDuration(loanType.getTotalDuration());
        dto.setProcessingSteps(processingStepToGetProcessingStepDTOListConverter
                .convert(loanType.getProcessingSteps()));
        return dto;
    }
}
