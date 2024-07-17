package com.fioneer.homework.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name="loan_processing_steps")
public class ProcessingStep {
    @Id
    @Column(columnDefinition = "TEXT")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int orderNo;

    @Column(nullable = false)
    private int expectedDuration;

    @ManyToOne
    private LoanType loanType;

    @PrePersist
    public void generateUUID() {
        this.id = UUID.randomUUID().toString();
    }
}
