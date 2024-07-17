package com.fioneer.homework.service.impl;

import com.fioneer.homework.enums.ProcessingStatus;
import com.fioneer.homework.enums.RequestStatus;
import com.fioneer.homework.exception.LoanRequestNotFoundException;
import com.fioneer.homework.exception.LoanTypeNotFoundException;
import com.fioneer.homework.exception.RequestStepNotFoundException;
import com.fioneer.homework.model.LoanRequest;
import com.fioneer.homework.model.LoanType;
import com.fioneer.homework.model.RequestStep;
import com.fioneer.homework.repository.LoanRequestRepository;
import com.fioneer.homework.repository.LoanTypeRepository;
import com.fioneer.homework.service.LoanRequestService;
import com.fioneer.homework.service.loanRequestCalculator.LoanRequestCalculationService;
import com.fioneer.homework.web.dto.loanRequest.CreateLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.GetLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.UpdateLoanRequestStatusDTO;
import com.fioneer.homework.web.dto.requestStep.UpdateRequestStepStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoanRequestServiceImpl implements LoanRequestService {

    private final ConversionService conversionService;
    private final LoanTypeRepository loanTypeRepository;
    private final LoanRequestRepository loanRequestRepository;
    private final LoanRequestCalculationService calculationService;

    @Override
    @Transactional
    public GetLoanRequestDTO save(CreateLoanRequestDTO createLoanRequestDTO) {
        LoanRequest loanRequestToSave = conversionService.convert(createLoanRequestDTO, LoanRequest.class);
        LoanType loanType = loanTypeRepository.findById(createLoanRequestDTO.getLoanTypeId())
                .orElseThrow(() -> new LoanTypeNotFoundException(createLoanRequestDTO.getLoanTypeId()));

        loanRequestToSave.setLoanType(loanType);
        loanRequestToSave.setRequestStatus(RequestStatus.PROCESSING);

        List<RequestStep> requestSteps = createRequestStepsForLoanType(loanRequestToSave, loanType);

        loanRequestToSave.setRequestSteps(requestSteps);
        calculationService.calculateAndSetTotalTimes(loanRequestToSave);

        LoanRequest savedLoanRequest = loanRequestRepository.save(loanRequestToSave);
        return conversionService.convert(savedLoanRequest, GetLoanRequestDTO.class);
    }

    @Override
    public List<GetLoanRequestDTO> findRequestsByStatus(RequestStatus status) {
        List<LoanRequest> loanRequests = loanRequestRepository.findByRequestStatus(status);
        return loanRequests.stream()
                .map(loanRequest -> conversionService.convert(loanRequest, GetLoanRequestDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateLoanRequestStatus(String loanRequestId, UpdateLoanRequestStatusDTO updateStatusDTO) {
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new LoanRequestNotFoundException(loanRequestId));

        loanRequest.setRequestStatus(updateStatusDTO.getNewStatus());
        loanRequestRepository.save(loanRequest);
    }

    @Override
    @Transactional
    public void updateRequestStepStatus(String loanRequestId, String stepId, UpdateRequestStepStatusDTO updateStatusDTO) {
        LoanRequest loanRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new LoanRequestNotFoundException(loanRequestId));

        RequestStep requestStep = loanRequest.getRequestSteps().stream()
                .filter(step -> step.getId().equals(stepId))
                .findFirst()
                .orElseThrow(() -> new RequestStepNotFoundException(stepId));

        requestStep.setProcessingStatus(updateStatusDTO.getNewStatus());
        requestStep.setActualDurationDays(updateStatusDTO.getActualDurationDays());

        if (updateStatusDTO.getNewStatus() == ProcessingStatus.FAILED) {
            loanRequest.setRequestStatus(RequestStatus.REJECTED);
        } else if (loanRequest.getRequestSteps().stream()
                .allMatch(step -> step.getProcessingStatus() == ProcessingStatus.SUCCESSFUL)) {
            loanRequest.setRequestStatus(RequestStatus.APPROVED);
        }

        loanRequestRepository.save(loanRequest);
    }

    private List<RequestStep> createRequestStepsForLoanType(LoanRequest loanRequestToSave, LoanType loanType) {
        return loanType.getProcessingSteps().stream()
                .map(processingStep -> {
                    RequestStep requestStep = new RequestStep();
                    requestStep.setExpectedDurationDays(processingStep.getExpectedDuration());
                    requestStep.setProcessingStatus(ProcessingStatus.PENDING);
                    requestStep.setLoanRequest(loanRequestToSave);
                    requestStep.setOrderNo(processingStep.getOrderNo());
                    requestStep.setProcessingStep(processingStep);
                    return requestStep;
                })
                .collect(Collectors.toList());
    }

}
