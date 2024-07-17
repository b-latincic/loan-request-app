package com.fioneer.homework.web.dto.requestStep;

import com.fioneer.homework.enums.ProcessingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRequestStepDTO {
    private String id;
    private int expectedDurationDays;
    private int actualDurationDays;
    private int orderNo;
    private ProcessingStatus processingStatus;

}
