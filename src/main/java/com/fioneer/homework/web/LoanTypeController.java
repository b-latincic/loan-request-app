package com.fioneer.homework.web;

import com.fioneer.homework.service.LoanTypeService;
import com.fioneer.homework.web.dto.loanType.CreateLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.GetLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.UpdateLoanTypeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/v1/loan-type")
@Validated
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @GetMapping
    public ResponseEntity<List<GetLoanTypeDTO>> getLoanTypes(
            @RequestParam(value="name", required = false) String name) {
        List<GetLoanTypeDTO> loanTypes = loanTypeService.findAllLoanTypesByName(name);
        return new ResponseEntity<>(loanTypes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GetLoanTypeDTO> create(
            @Valid @RequestBody CreateLoanTypeDTO createLoanTypeDTO) {
        GetLoanTypeDTO savedLoanTypeDTO = loanTypeService.save(createLoanTypeDTO);
        return new ResponseEntity<>(savedLoanTypeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetLoanTypeDTO> update(
            @Valid @PathVariable String id,
            @RequestBody UpdateLoanTypeDTO updateLoanTypeDTO) {
        GetLoanTypeDTO updatedLoanType = loanTypeService.update(id, updateLoanTypeDTO);
        return new ResponseEntity<>(updatedLoanType, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable String id) {
        boolean isDeleted = loanTypeService.deleteLoanType(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}
