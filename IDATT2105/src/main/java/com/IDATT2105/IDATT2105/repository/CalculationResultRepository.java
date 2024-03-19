package com.IDATT2105.IDATT2105.repository;

import com.IDATT2105.IDATT2105.model.CalculationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationResultRepository extends JpaRepository<CalculationResult, Long> {
}
