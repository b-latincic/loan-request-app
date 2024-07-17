package com.fioneer.homework.repository;

import com.fioneer.homework.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {

    @Query("SELECT l FROM LoanType l WHERE " +
            "(:name IS NULL OR :name = '' OR LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<LoanType> findAll(@Param("name") String name);

}
