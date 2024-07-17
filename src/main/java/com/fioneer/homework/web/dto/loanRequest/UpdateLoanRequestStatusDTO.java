package com.fioneer.homework.web.dto.loanRequest;

import com.fioneer.homework.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLoanRequestStatusDTO {
    private RequestStatus newStatus;
}
