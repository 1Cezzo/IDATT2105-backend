package com.IDATT2105.IDATT2105.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IDATT2105.IDATT2105.model.CalculationRequest;
import com.IDATT2105.IDATT2105.model.CalculationResult;
import com.IDATT2105.IDATT2105.service.UserService;
import com.IDATT2105.IDATT2105.service.CalculationResultService;
import com.IDATT2105.IDATT2105.model.User;

@RestController
public class CalculationResultController {

    private final CalculationResultService calculationResultService;
    private final UserService userService;

    @Autowired
    public CalculationResultController(CalculationResultService calculationResultService, UserService userService) {
        this.calculationResultService = calculationResultService;
        this.userService = userService;
    }

    @PostMapping("/calculate")
      public ResponseEntity<Double> calculate(@RequestBody CalculationRequest calculationRequest) {
          try {
              String equation = calculationRequest.getEquation();
              User user = calculationRequest.getUser();

              double result = calculationResultService.calculate(equation);
              CalculationResult calculationResult = new CalculationResult();
              calculationResult.setExpression(equation);
              calculationResult.setAnswer(result);
              calculationResult.setUser(user);
              calculationResultService.saveCalculationResult(calculationResult);

              return ResponseEntity.ok(result);
          } catch (Exception e) {
              return ResponseEntity.badRequest().body(null);
          }
      }
}
