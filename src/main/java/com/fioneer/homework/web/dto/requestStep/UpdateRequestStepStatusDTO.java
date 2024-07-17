package com.fioneer.homework.web.dto.requestStep;

import com.fioneer.homework.enums.ProcessingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestStepStatusDTO {
    private ProcessingStatus newStatus;
    private int actualDurationDays;
}
