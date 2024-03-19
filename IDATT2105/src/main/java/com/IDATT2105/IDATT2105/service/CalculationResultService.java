package com.IDATT2105.IDATT2105.service;

import com.IDATT2105.IDATT2105.model.CalculationResult;
import com.IDATT2105.IDATT2105.repository.CalculationResultRepository;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculationResultService {

    @Autowired
    private CalculationResultRepository calculationResultRepository;

    public CalculationResultService(CalculationResultRepository calculationResultRepository) {
        this.calculationResultRepository = calculationResultRepository;
    }

    public double calculate(String equation) {
      log.info("Handling request to calculate: " + equation);
      try {
          Expression expression = new ExpressionBuilder(equation).build();
          double result = expression.evaluate();
          return result;
      } catch (ArithmeticException | IllegalArgumentException e) {
          log.error("Error occurred while calculating: " + equation, e);
          throw new IllegalArgumentException("Invalid equation: " + equation);
      }
    }

    public CalculationResult saveCalculationResult(CalculationResult calculationResult) {
      return calculationResultRepository.save(calculationResult);
    }

    public Page<CalculationResult> getCalculationResultsByUserId(Long userId, Pageable pageable) {
    return calculationResultRepository.findByUserId(userId, pageable);
    }
  
}
