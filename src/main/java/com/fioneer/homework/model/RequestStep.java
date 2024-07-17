package com.fioneer.homework.model;

import com.fioneer.homework.enums.ProcessingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name="loan_request_steps")
public class RequestStep {
    @Id
    @Column(columnDefinition = "TEXT")
    private String id;

    @Column
    private int orderNo;

    @Column
    private int expectedDurationDays;

    @Column
    private int actualDurationDays;

    @Enumerated(EnumType.STRING)
    @Column
    private ProcessingStatus processingStatus;

    @ManyToOne
    private LoanRequest loanRequest;

    @ManyToOne
    private ProcessingStep processingStep;

    @PrePersist
    public void generateUUID() {
        this.id = UUID.randomUUID().toString();
    }
}
