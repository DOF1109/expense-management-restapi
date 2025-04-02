package com.danielfrias.restapi.service.impl;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.entity.ExpenseEntity;
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
        log.info("Printing the data from repository \n{}", list);
        // Convert the list of Entity objects to DTO objects
        List<ExpenseDTO> listOfExpenses = list.stream()
                .map(expenseEntity -> modelMapper.map(expenseEntity, ExpenseDTO.class))
                .toList();
        return listOfExpenses;
    }
}
