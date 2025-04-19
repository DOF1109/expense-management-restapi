package com.danielfrias.restapi.controller;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.io.ExpenseRequest;
import com.danielfrias.restapi.io.ExpenseResponse;
import com.danielfrias.restapi.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
                .map(expenseDTO -> mapExpenseDTOToExpenseResponse(expenseDTO))
                .toList();
        return response;
    }

    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        ExpenseDTO expenseDTO = expenseService.getExpenseById(expenseId);
        return mapExpenseDTOToExpenseResponse(expenseDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{expenseId}")
    public void deleteExpenseByExpenseId(@PathVariable String expenseId) {
        expenseService.deleteExpenseByExpenseId(expenseId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expenses")
    public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest expenseRequest) {
        ExpenseDTO expenseDTO = mapExpenseRequestToExpenseDTO(expenseRequest);
        expenseDTO = expenseService.saveExpenseDetails(expenseDTO);
        return mapExpenseDTOToExpenseResponse(expenseDTO);
    }

    private ExpenseResponse mapExpenseDTOToExpenseResponse(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }

    private ExpenseDTO mapExpenseRequestToExpenseDTO(ExpenseRequest expenseRequest) {
        return modelMapper.map(expenseRequest, ExpenseDTO.class);
    }

}
