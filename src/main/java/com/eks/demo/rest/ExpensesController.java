package com.eks.demo.rest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eks.demo.dto.ExpenseDTO;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	Map<String, ExpenseDTO> expenses = new ConcurrentHashMap<String, ExpenseDTO>();

	@GetMapping("/{expenseId}")
	public ExpenseDTO getExpensById(@PathVariable String expenseId) {
		logger.info("Finding expense with id {}", expenseId);
		if (expenses.containsKey(expenseId)) {
			return expenses.get(expenseId);
		}

		throw new RuntimeException("Expense with id " + expenseId + " not found");
	}

	@PostMapping("")
	public ExpenseDTO createExpense(@RequestBody ExpenseDTO expense) {

		String expenseID = UUID.randomUUID().toString();
		expense.setId(expenseID);
		expense.setDateTime(LocalDateTime.now());
		expenses.put(expenseID, expense);
		logger.info("Created expense with id {}", expenseID);
		return expense;
	}

	@PutMapping("/{expenseId}")
	public ExpenseDTO updateExpense(@PathVariable String expenseId, @RequestBody ExpenseDTO expense) {
		logger.info("Updating expense with id {}", expenseId);
		expenses.put(expense.getId(), expense);
		return expense;
	}

	@DeleteMapping("/{expenseId}")
	public void updateExpense(@PathVariable String expenseId) {
		logger.info("Deleting expense with id {}", expenseId);
		expenses.remove(expenseId);
	}
}
