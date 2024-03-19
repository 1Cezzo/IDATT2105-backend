package com.IDATT2105.IDATT2105.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calculation_results")
public class CalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "answer")
    private double answer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
