package com.fioneer.homework.service.impl;

import com.fioneer.homework.exception.LoanTypeNotFoundException;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.model.ProcessingStep;
import com.fioneer.homework.repository.LoanTypeRepository;
import com.fioneer.homework.repository.ProcessingStepRepository;
import com.fioneer.homework.service.LoanTypeService;
import com.fioneer.homework.web.dto.loanType.CreateLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.GetLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.UpdateLoanTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanTypeServiceImpl implements LoanTypeService {

    private final ConversionService conversionService;
    private final LoanTypeRepository loanTypeRepo;
    private final ProcessingStepRepository processingStepRepository;

    @Override
    public List<GetLoanTypeDTO> findAllLoanTypesByName(String name) {
            return loanTypeRepo.findAll(name)
                    .stream()
                    .map(loanType -> conversionService.convert(loanType, GetLoanTypeDTO.class))
                    .toList();
    }

    @Override
    @Transactional
    public GetLoanTypeDTO save(CreateLoanTypeDTO createLoanTypeDTO) {
        LoanType loanTypeToSave = conversionService.convert(createLoanTypeDTO, LoanType.class);
        List<ProcessingStep> steps = processingStepRepository.findAllById(createLoanTypeDTO.getStepIds());
        steps.forEach(step -> step.setLoanType(loanTypeToSave));
        loanTypeToSave.setProcessingSteps(steps);
        loanTypeToSave.updateTotalDuration();
        LoanType savedLoanType = loanTypeRepo.save(loanTypeToSave);
        return conversionService.convert(savedLoanType, GetLoanTypeDTO.class);
    }

    @Override
    @Transactional
    public GetLoanTypeDTO update(String id, UpdateLoanTypeDTO updateLoanTypeDTO) {
        LoanType loanTypeToUpdate = conversionService.convert(updateLoanTypeDTO, LoanType.class);
        LoanType existingLoanType = loanTypeRepo.findById(id)
                .orElseThrow(() -> new LoanTypeNotFoundException(id));

        updateLoanType(existingLoanType, loanTypeToUpdate);
        existingLoanType.updateTotalDuration();
        LoanType savedLoanType = loanTypeRepo.save(existingLoanType);
        return conversionService.convert(savedLoanType, GetLoanTypeDTO.class);
    }

    @Override
    public boolean deleteLoanType(String id) {
        boolean doesLoanTypeExist = loanTypeRepo.existsById(id);
        if(!doesLoanTypeExist) {
            throw new LoanTypeNotFoundException(id);
        }
        loanTypeRepo.deleteById(id);
        return true;
    }

    private void updateLoanType(LoanType existingLoanType, LoanType loanTypeToUpdate) {
        existingLoanType.setName(loanTypeToUpdate.getName());
    }

}
