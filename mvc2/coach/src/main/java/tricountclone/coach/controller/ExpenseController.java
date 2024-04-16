package tricountclone.coach.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tricountclone.coach.model.ExpenseRequest;
import tricountclone.coach.service.ExpenseService;

@RestController
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/expenses/add")
    public ResponseEntity<Void> addExpenseToSettlement(@RequestBody @Valid ExpenseRequest expenseRequest) {
        expenseService.addExpense(expenseRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
