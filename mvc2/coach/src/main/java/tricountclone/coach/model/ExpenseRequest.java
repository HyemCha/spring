package tricountclone.coach.model;

import lombok.Data;
import lombok.NonNull;
import tricountclone.coach.MemberContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseRequest {
    @NonNull
    private String name;
    @NonNull
    private Long settlementId;
    private Long payerMemberId = MemberContext.getCurrentMember().getId();
    @NonNull
    private BigDecimal amount;
}
