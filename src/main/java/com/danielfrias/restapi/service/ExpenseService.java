package com.danielfrias.restapi.service;

import com.danielfrias.restapi.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDTO> getAllExpenses();

    ExpenseDTO getExpenseById(String expenseId);

}
