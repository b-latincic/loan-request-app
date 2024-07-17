package com.fioneer.homework.converter.processingStep;

import com.fioneer.homework.model.ProcessingStep;
import com.fioneer.homework.web.dto.processingStep.CreateProcessingStepDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateProcessingStepDTOToProcessingStepConverter implements Converter<CreateProcessingStepDTO, ProcessingStep> {

    @Override
    public ProcessingStep convert(CreateProcessingStepDTO dto) {
        ProcessingStep processingStep = new ProcessingStep();
        processingStep.setName(dto.getName());
        processingStep.setExpectedDuration(dto.getExpectedDuration());
        processingStep.setOrderNo(dto.getOrderNo());
        return processingStep;
    }
}
