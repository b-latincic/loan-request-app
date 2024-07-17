package com.fioneer.homework.service.loanRequestCalculator;

import com.fioneer.homework.model.LoanRequest;
import com.fioneer.homework.model.RequestStep;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanRequestCalculationService {
    public void calculateAndSetTotalTimes(LoanRequest loanRequest) {
        // Calculating total expected time for a loan request
        int totalExpectedTime = calculateTotalExpectedTime(loanRequest.getRequestSteps());
        int totalSpentTime = calculateTotalSpentTime(loanRequest.getRequestSteps());

        loanRequest.setTotalExpectedTime(totalExpectedTime);
        loanRequest.setTotalSpentTime(totalSpentTime);
    }

    private int calculateTotalExpectedTime(List<RequestStep> requestSteps) {
        // Calculating total expected time based on request steps
        return requestSteps.stream()
                .mapToInt(RequestStep::getExpectedDurationDays)
                .sum();
    }

    private int calculateTotalSpentTime(List<RequestStep> requestSteps) {
        // Calculating total spent time based on request steps
        return requestSteps.stream()
                .filter(step -> step.getActualDurationDays() != -1)
                .mapToInt(RequestStep::getActualDurationDays)
                .sum();
    }

}
