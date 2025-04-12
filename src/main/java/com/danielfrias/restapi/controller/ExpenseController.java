package com.danielfrias.restapi.controller;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.io.ExpenseResponse;
import com.danielfrias.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*") /* Temporary solution for development porpoises */
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        // Convert the list of DTO objects to Response objects
        List<ExpenseResponse> response = list.stream()
                .map(expenseDTO -> mapToExpenseResponse(expenseDTO))
                .toList();
        return response;
    }

    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        ExpenseDTO expenseDTO = expenseService.getExpenseById(expenseId);
        return mapToExpenseResponse(expenseDTO);
    }

    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }

}
