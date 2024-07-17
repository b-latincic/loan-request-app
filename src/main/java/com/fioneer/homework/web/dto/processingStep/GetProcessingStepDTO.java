package com.fioneer.homework.web.dto.processingStep;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetProcessingStepDTO {

    private String id;
    private String name;
    private int orderNo;
    private int expectedDuration;

}
