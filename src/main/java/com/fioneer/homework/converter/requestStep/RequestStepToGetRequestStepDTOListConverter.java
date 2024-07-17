package com.fioneer.homework.converter.requestStep;

import com.fioneer.homework.model.RequestStep;
import com.fioneer.homework.web.dto.requestStep.GetRequestStepDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RequestStepToGetRequestStepDTOListConverter implements Converter<List<RequestStep>, List<GetRequestStepDTO>> {

    public GetRequestStepDTO convert(RequestStep requestStep) {
        GetRequestStepDTO dto = new GetRequestStepDTO();
        dto.setId(requestStep.getId());
        dto.setExpectedDurationDays(requestStep.getExpectedDurationDays());
        dto.setActualDurationDays(requestStep.getActualDurationDays());
        dto.setOrderNo(requestStep.getOrderNo());
        dto.setProcessingStatus(requestStep.getProcessingStatus());
        return dto;
    }


    @Override
    public List<GetRequestStepDTO> convert(List<RequestStep> steps) {
        return steps.stream()
                .map(this::convert)
                .toList();
    }
}
