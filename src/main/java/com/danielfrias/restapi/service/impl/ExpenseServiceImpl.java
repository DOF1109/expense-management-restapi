package com.danielfrias.restapi.service.impl;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.entity.ExpenseEntity;
import com.danielfrias.restapi.exceptions.ResourceNotFoundException;
import com.danielfrias.restapi.repository.ExpenseRepository;
import com.danielfrias.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing all expenses from repository \n{}", list);
        // Convert the list of Entity objects to DTO objects
        List<ExpenseDTO> listOfExpenses = list.stream()
                .map(expenseEntity -> mapToExpenseDTO(expenseEntity))
                .toList();
        return listOfExpenses;
    }

    @Override
    public ExpenseDTO getExpenseById(String expenseId) {
        ExpenseEntity optionalExpense = expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Not founded expense whit id: " + expenseId));
        log.info("Printing a expense from repository \n{}", optionalExpense);
        return mapToExpenseDTO(optionalExpense);
    }

    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }

}
