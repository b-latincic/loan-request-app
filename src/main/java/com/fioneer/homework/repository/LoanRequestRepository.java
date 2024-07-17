package com.fioneer.homework.repository;

import com.fioneer.homework.enums.RequestStatus;
import com.fioneer.homework.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, String> {
    List<LoanRequest> findByRequestStatus(RequestStatus status);
}
