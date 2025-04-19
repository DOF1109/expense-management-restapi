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
import java.util.UUID;

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
                .map(expenseEntity -> mapExpenseEntityToExpenseDTO(expenseEntity))
                .toList();
        return listOfExpenses;
    }

    @Override
    public ExpenseDTO getExpenseById(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing a expense from repository \n{}", expenseEntity);
        return mapExpenseEntityToExpenseDTO(expenseEntity);
    }

    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Deleting the expense with id: {}", expenseId);
        expenseRepository.delete(expenseEntity);
    }

    @Override
    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseEntity newExpenseEntity = mapExpenseDTOToExpenseEntity(expenseDTO);
        newExpenseEntity.setExpenseId(UUID.randomUUID().toString());
        newExpenseEntity = expenseRepository.save(newExpenseEntity);
        log.info("Saving a new expense with id: {}", newExpenseEntity.getExpenseId());
        return mapExpenseEntityToExpenseDTO(newExpenseEntity);
    }

    private ExpenseDTO mapExpenseEntityToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }

    private ExpenseEntity mapExpenseDTOToExpenseEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, ExpenseEntity.class);
    }

    private ExpenseEntity getExpenseEntity(String expenseId) {
        return expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Not founded expense with id: " + expenseId));
    }

}
