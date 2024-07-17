package com.fioneer.homework.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name="loan_types")
public class LoanType {

    @Id
    @Column(columnDefinition = "TEXT")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "loanType", cascade = CascadeType.ALL)
    private List<ProcessingStep> processingSteps = new ArrayList<>();

    @Column
    private int totalDuration;

    public void updateTotalDuration() {
        this.totalDuration = processingSteps.stream().mapToInt(ProcessingStep::getExpectedDuration).sum();
    }

    @PrePersist
    public void generateUUID() {
        this.id = UUID.randomUUID().toString();
    }
}
