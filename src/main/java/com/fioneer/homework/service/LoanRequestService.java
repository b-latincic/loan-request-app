package com.fioneer.homework.service;

import com.fioneer.homework.enums.RequestStatus;
import com.fioneer.homework.web.dto.loanRequest.CreateLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.GetLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.UpdateLoanRequestStatusDTO;
import com.fioneer.homework.web.dto.requestStep.UpdateRequestStepStatusDTO;
import java.util.List;

public interface LoanRequestService {
    GetLoanRequestDTO save(CreateLoanRequestDTO createLoanRequestDTO);
    List<GetLoanRequestDTO> findRequestsByStatus(RequestStatus status);
    void updateLoanRequestStatus(String loanRequestId, UpdateLoanRequestStatusDTO updateStatusDTO);
    void updateRequestStepStatus(String loanRequestId, String stepId, UpdateRequestStepStatusDTO updateStatusDTO);
}
