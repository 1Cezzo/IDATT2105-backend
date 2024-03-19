package com.IDATT2105.IDATT2105.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

      @GetMapping("/calculation-results/{userId}")
        public ResponseEntity<Page<CalculationResult>> getCalculationResultsByUserId(
                @PathVariable Long userId,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "id,desc") String sort) {
            try {
                String[] sortArray = sort.split(",");
                String sortBy = sortArray[0];
                String sortOrder = sortArray.length > 1 ? sortArray[1] : "desc"; // Assuming ascending order if not specified
                Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
                Page<CalculationResult> calculationResultsPage = calculationResultService.getCalculationResultsByUserId(userId, pageable);
                return ResponseEntity.ok(calculationResultsPage);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

}

