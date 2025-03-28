package com.danielfrias.restapi.service.impl;

import com.danielfrias.restapi.dto.ExpenseDTO;
import com.danielfrias.restapi.entity.ExpenseEntity;
import com.danielfrias.restapi.repository.ExpenseRepository;
import com.danielfrias.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        List<ExpenseEntity> list = expenseRepository.findAll();
        // Convert the list of Entity objects to DTO objects
        List<ExpenseDTO> listOfExpenses = list.stream()
                .map(expenseEntity -> modelMapper.map(expenseEntity, ExpenseDTO.class))
                .toList();
        return listOfExpenses;
    }
}
