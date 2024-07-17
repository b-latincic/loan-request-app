package com.fioneer.homework.config;

import com.fioneer.homework.converter.loanRequest.CreateLoanRequestDTOToLoanRequestConverter;
import com.fioneer.homework.converter.loanRequest.LoanRequestToGetLoanRequestDTOConverter;
import com.fioneer.homework.converter.loanType.CreateLoanTypeDTOToLoanTypeConverter;
import com.fioneer.homework.converter.loanType.UpdateLoanTypeDTOToLoanTypeConverter;
import com.fioneer.homework.converter.processingStep.CreateProcessingStepDTOToProcessingStepConverter;
import com.fioneer.homework.converter.loanType.LoanTypeToGetLoanTypeDTOConverter;
import com.fioneer.homework.converter.loanType.LoanTypeToGetLoanTypeDTOListConverter;
import com.fioneer.homework.converter.processingStep.ProcessingStepToGetProcessingStepDTOConverter;
import com.fioneer.homework.converter.processingStep.ProcessingStepToGetProcessingStepDTOListConverter;
import com.fioneer.homework.converter.requestStep.RequestStepToGetRequestStepDTOListConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

    private final ProcessingStepToGetProcessingStepDTOListConverter processingStepToGetProcessingStepDTOListConverter;
    private final RequestStepToGetRequestStepDTOListConverter requestStepToGetRequestStepDTOtoListConverter;


    @Bean
    public DefaultFormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        // Loan type

        conversionService.addConverter(new LoanTypeToGetLoanTypeDTOConverter(processingStepToGetProcessingStepDTOListConverter));
        conversionService.addConverter(new LoanTypeToGetLoanTypeDTOListConverter());
        conversionService.addConverter(new CreateLoanTypeDTOToLoanTypeConverter());
        conversionService.addConverter(new UpdateLoanTypeDTOToLoanTypeConverter());

        // Processing step
        conversionService.addConverter(new ProcessingStepToGetProcessingStepDTOListConverter());
        conversionService.addConverter(new CreateProcessingStepDTOToProcessingStepConverter());
        conversionService.addConverter(new ProcessingStepToGetProcessingStepDTOConverter());

        // Loan request
        conversionService.addConverter(new CreateLoanRequestDTOToLoanRequestConverter());
        conversionService.addConverter(new LoanRequestToGetLoanRequestDTOConverter(requestStepToGetRequestStepDTOtoListConverter));

        return conversionService;
    }

}
