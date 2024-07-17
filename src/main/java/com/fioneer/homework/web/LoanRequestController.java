package com.fioneer.homework.web;

import com.fioneer.homework.enums.RequestStatus;
import com.fioneer.homework.service.LoanRequestService;
import com.fioneer.homework.web.dto.loanRequest.CreateLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.GetLoanRequestDTO;
import com.fioneer.homework.web.dto.loanRequest.UpdateLoanRequestStatusDTO;
import com.fioneer.homework.web.dto.requestStep.UpdateRequestStepStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/loan-request")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;

    @GetMapping("/status/{status}")
    public ResponseEntity<List<GetLoanRequestDTO>> getLoanRequestsByStatus(
            @PathVariable RequestStatus status) {
        List<GetLoanRequestDTO> loanRequests = loanRequestService.findRequestsByStatus(status);
        return ResponseEntity.ok(loanRequests);
    }

    @PostMapping
    public ResponseEntity<GetLoanRequestDTO> createLoanRequest(
            @RequestBody CreateLoanRequestDTO createLoanRequestDTO) {
        GetLoanRequestDTO savedLoanRequestDTO = loanRequestService.save(createLoanRequestDTO);
        return new ResponseEntity<>(savedLoanRequestDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{loanRequestId}/status")
    public ResponseEntity<Void> updateLoanRequestStatus(
            @PathVariable String loanRequestId,
            @RequestBody UpdateLoanRequestStatusDTO updateStatusDTO) {
        loanRequestService.updateLoanRequestStatus(loanRequestId, updateStatusDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{loanRequestId}/steps/{stepId}/status")
    public ResponseEntity<Void> updateRequestStepStatus(
            @PathVariable String loanRequestId,
            @PathVariable String stepId,
            @RequestBody UpdateRequestStepStatusDTO updateStatusDTO) {
        loanRequestService.updateRequestStepStatus(loanRequestId, stepId, updateStatusDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
