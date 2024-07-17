package com.fioneer.homework.converter.processingStep;

import com.fioneer.homework.model.ProcessingStep;
import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProcessingStepToGetProcessingStepDTOConverter implements Converter<ProcessingStep, GetProcessingStepDTO> {

    @Override
    public GetProcessingStepDTO convert(ProcessingStep processingStep) {
        GetProcessingStepDTO dto = new GetProcessingStepDTO();
        dto.setExpectedDuration(processingStep.getExpectedDuration());
        dto.setName(processingStep.getName());
        dto.setOrderNo(processingStep.getOrderNo());
        dto.setId(processingStep.getId());
        return dto;
    }
}
