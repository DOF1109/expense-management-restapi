package com.danielfrias.restapi.controller;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.io.ExpenseResponse;
import com.danielfrias.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        // Convert the list of DTO objects to Response objects
        List<ExpenseResponse> response = list.stream()
                .map(expenseDTO -> modelMapper.map(expenseDTO, ExpenseResponse.class))
                .toList();
        return response;
    }
}
