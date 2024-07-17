package com.fioneer.homework.converter.processingStep;

import com.fioneer.homework.model.ProcessingStep;
import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProcessingStepToGetProcessingStepDTOListConverter implements Converter<List<ProcessingStep>, List<GetProcessingStepDTO>> {

    public GetProcessingStepDTO convert(ProcessingStep step) {
        GetProcessingStepDTO dto = new GetProcessingStepDTO();
        dto.setId(step.getId());
        dto.setName(step.getName());
        dto.setOrderNo(step.getOrderNo());
        dto.setExpectedDuration(step.getExpectedDuration());
        return dto;
    }

    @Override
    public List<GetProcessingStepDTO> convert(List<ProcessingStep> steps) {
        return steps.stream()
                .map(this::convert)
                .toList();
    }
}
