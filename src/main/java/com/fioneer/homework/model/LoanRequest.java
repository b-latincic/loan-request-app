package com.fioneer.homework.model;

import com.fioneer.homework.enums.RequestStatus;
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
@Table(name="loan_requests")
public class LoanRequest {
    @Id
    @Column(columnDefinition = "TEXT")
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private double loanAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus requestStatus;

    @Column
    private int totalExpectedTime;

    @Column
    private int totalSpentTime;

    @OneToMany(mappedBy = "loanRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequestStep> requestSteps = new ArrayList<>();

    @ManyToOne
    private LoanType loanType;

    @PrePersist
    public void generateUUID() {
        this.id = UUID.randomUUID().toString();
    }
}
