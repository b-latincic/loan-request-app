package com.fioneer.homework.converter.loanType;

import com.fioneer.homework.converter.processingStep.ProcessingStepToGetProcessingStepDTOListConverter;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.web.dto.loanType.GetLoanTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanTypeToGetLoanTypeDTOListConverter implements Converter<List<LoanType>, List<GetLoanTypeDTO>> {

    @Autowired
    private ProcessingStepToGetProcessingStepDTOListConverter processingStepToGetProcessingStepDTOListConverter;

    public GetLoanTypeDTO convert (LoanType loanType) {
        GetLoanTypeDTO dto = new GetLoanTypeDTO();
        dto.setTotalDuration(loanType.getTotalDuration());
        dto.setName(loanType.getName());
        dto.setId(loanType.getId());
        dto.setProcessingSteps(processingStepToGetProcessingStepDTOListConverter
                .convert(loanType.getProcessingSteps()));
        return dto;
    }

    @Override
    public List<GetLoanTypeDTO> convert(List<LoanType> loanTypes) {
        return loanTypes.stream()
                .map(this::convert)
                .toList();
    }
}
