package com.danielfrias.restapi.controller;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.io.ExpenseResponse;
import com.danielfrias.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        log.info("Printing the data from service \n{}", list);
        // Convert the list of DTO objects to Response objects
        List<ExpenseResponse> response = list.stream()
                .map(expenseDTO -> modelMapper.map(expenseDTO, ExpenseResponse.class))
                .toList();
        return response;
    }
}
