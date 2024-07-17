package com.fioneer.homework.service.impl;

import com.fioneer.homework.exception.LoanTypeNotFoundException;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.model.ProcessingStep;
import com.fioneer.homework.repository.LoanTypeRepository;
import com.fioneer.homework.repository.ProcessingStepRepository;
import com.fioneer.homework.service.ProcessingStepService;
import com.fioneer.homework.web.dto.processingStep.CreateProcessingStepDTO;
import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProcessingStepServiceImpl implements ProcessingStepService {

    private final ConversionService conversionService;
    private final ProcessingStepRepository processingStepRepo;
    private final LoanTypeRepository loanTypeRepository;

    @Override
    @Transactional
    public GetProcessingStepDTO save(CreateProcessingStepDTO createProcessingStepDTO) {
        ProcessingStep processingStepToSave = conversionService.convert(createProcessingStepDTO, ProcessingStep.class);
        LoanType loanType = loanTypeRepository.findById(createProcessingStepDTO.getLoanTypeId())
                .orElseThrow(() -> new LoanTypeNotFoundException(createProcessingStepDTO.getLoanTypeId()));
        processingStepToSave.setLoanType(loanType);

        loanType.getProcessingSteps().add(processingStepToSave);
        loanType.updateTotalDuration();
        LoanType savedLoanType = loanTypeRepository.save(loanType);
        ProcessingStep savedProcessingStep = processingStepRepo.save(processingStepToSave);
        return conversionService.convert(savedProcessingStep, GetProcessingStepDTO.class);
    }
}
