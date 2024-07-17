package com.fioneer.homework.web;

import com.fioneer.homework.service.ProcessingStepService;
import com.fioneer.homework.web.dto.processingStep.CreateProcessingStepDTO;
import com.fioneer.homework.web.dto.processingStep.GetProcessingStepDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/processing-steps")
@Validated
public class ProcessingStepController {

    private final ProcessingStepService processingStepService;

    @PostMapping
    public ResponseEntity<GetProcessingStepDTO> create(
            @Valid @RequestBody CreateProcessingStepDTO createProcessingStepDTO) {
        GetProcessingStepDTO savedProcessingStepDTO = processingStepService.save(createProcessingStepDTO);
        return new ResponseEntity<>(savedProcessingStepDTO, HttpStatus.CREATED);
    }
}
