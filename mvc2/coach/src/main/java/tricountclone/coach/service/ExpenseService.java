package tricountclone.coach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tricountclone.coach.model.Expense;
import tricountclone.coach.model.ExpenseRequest;
import tricountclone.coach.model.Settlement;
import tricountclone.coach.repository.ExpenseRepository;
import tricountclone.coach.repository.SettlementRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final SettlementRepository settlementRepository;
    private final ExpenseRepository expenseRepository;

    public void addExpense(ExpenseRequest expenseRequest) {
        // 정산방이 있는지 체크
        Optional<Settlement> settlement = settlementRepository.findById(expenseRequest.getSettlementId());
        if (!settlement.isPresent()) {
            throw new RuntimeException("invalid");
        }

        // 있다면 지출내역 저장
        Expense expense = new Expense();
        expense.setName(expenseRequest.getName());
        expense.setSettlementId(expense.getSettlementId());
        expense.setPayerMemberId(expense.getPayerMemberId());
        expense.setAmount(expense.getAmount());
        expense.setExpenseDateTime(LocalDateTime.now());

        expenseRepository.save(expense);
    }


}
