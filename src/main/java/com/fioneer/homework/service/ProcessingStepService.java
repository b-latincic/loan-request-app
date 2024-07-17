package com.fioneer.homework.service;

import com.fioneer.homework.web.dto.processingStep.CreateProcessingStepDTO;
import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;

public interface ProcessingStepService {

    GetProcessingStepDTO save(CreateProcessingStepDTO createProcessingStepDTO);
}
