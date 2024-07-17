package com.fioneer.homework.service;

import com.fioneer.homework.web.dto.loanType.CreateLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.GetLoanTypeDTO;
import com.fioneer.homework.web.dto.loanType.UpdateLoanTypeDTO;
import java.util.List;

public interface LoanTypeService {

    List<GetLoanTypeDTO> findAllLoanTypesByName(String name);

    GetLoanTypeDTO save(CreateLoanTypeDTO createLoanTypeDTO);

    GetLoanTypeDTO update(String id, UpdateLoanTypeDTO updateLoanTypeDTO);

    boolean deleteLoanType(String id);
}
