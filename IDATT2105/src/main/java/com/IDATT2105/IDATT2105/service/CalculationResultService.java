package com.IDATT2105.IDATT2105.service;

import com.IDATT2105.IDATT2105.model.CalculationResult;
import com.IDATT2105.IDATT2105.repository.CalculationResultRepository;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculationResultService {

    private final CalculationResultRepository calculationResultRepository;

    @Autowired
    public CalculationResultService(CalculationResultRepository calculationResultRepository) {
        this.calculationResultRepository = calculationResultRepository;
    }

    public double calculate(String equation) {
        log.info("Handling request to calculate: " + equation);
        Expression expression = new ExpressionBuilder(equation).build();
        double result = expression.evaluate();
        return result;
    }

    public CalculationResult saveCalculationResult(CalculationResult calculationResult) {
        return calculationResultRepository.save(calculationResult);
    }
}
