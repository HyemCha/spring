package tricountclone.coach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tricountclone.coach.MemberContext;
import tricountclone.coach.model.BalanceResult;
import tricountclone.coach.model.ExpenseResult;
import tricountclone.coach.model.Member;
import tricountclone.coach.model.Settlement;
import tricountclone.coach.repository.SettlementRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;

    public Settlement createSettlement(String settlementName) {
        Settlement settlement = settlementRepository.create(settlementName);
        settlementRepository.addParticipantToSettlement(settlement.getId(), MemberContext.getCurrentMember().getId());
        settlement.getParticipants().add(MemberContext.getCurrentMember());
        return settlement;
    }

    public void joinSettlement(Long settlementId) { //                  로그인된 사용자 정보 가져오기
        settlementRepository.addParticipantToSettlement(settlementId, MemberContext.getCurrentMember().getId());
    }

    public List<BalanceResult> getBalanceResult(Long settlementId) {
        // 누가 누구에게 얼마를 줘야돼 라는 목록을 만들어야 한다.
        Map<Member, List<ExpenseResult>> collect = settlementRepository.findExpensesWithMemberBySettlementId(settlementId)
                .stream().collect(Collectors.groupingBy(ExpenseResult::getPayerMember));

        Map<Member, BigDecimal> memberAmountSumMap = collect.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, memberListEntry ->
                        memberListEntry.getValue().stream()
                                .map(ExpenseResult::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ));

        BigDecimal sumAmount = memberAmountSumMap.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageAmount = sumAmount.divide(BigDecimal.valueOf(memberAmountSumMap.size()), BigDecimal.ROUND_DOWN);

        Map<Member, BigDecimal> calculatedAmountMap = memberAmountSumMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, memberBigDecimalEntry ->
                        memberBigDecimalEntry.getValue().subtract(averageAmount)));

        List<Map.Entry<Member, BigDecimal>> receivers = calculatedAmountMap.entrySet().stream()
                .filter(memberBigDecimalEntry -> memberBigDecimalEntry.getValue().signum() > 0)
                .sorted((o1, o2) -> o2.getValue().subtract(o1.getValue()).signum())
                .toList();

        List<Map.Entry<Member, BigDecimal>> senders = calculatedAmountMap.entrySet().stream()
                .filter(memberBigDecimalEntry -> memberBigDecimalEntry.getValue().signum() < 0)
                .sorted((o1, o2) -> o1.getValue().subtract(o2.getValue()).signum())
                .toList();

        List<BalanceResult> balanceResults = new ArrayList<>();

        int receiverIdx = 0;
        int senderIdx = 0;
        while (receiverIdx < receivers.size() && senderIdx < senders.size()) {
            BigDecimal amountToTransfer = receivers.get(receiverIdx).getValue().add(senders.get(senderIdx).getValue());

            if (amountToTransfer.signum() < 0) {
                balanceResults.add(new BalanceResult(
                        senders.get(senderIdx).getKey().getId(),
                        senders.get(senderIdx).getKey().getName(),
                        receivers.get(receiverIdx).getValue().abs(),
                        receivers.get(receiverIdx).getKey().getId(),
                        receivers.get(receiverIdx).getKey().getName()
                ));
                receivers.get(receiverIdx).setValue(BigDecimal.ZERO);
                senders.get(senderIdx).setValue(amountToTransfer);
                receiverIdx++;
            } else if (amountToTransfer.signum() > 0) { // receiver : 2000  sender : -1000 -> receiver가 받아야하는 돈이 더 많은 경우
                balanceResults.add(new BalanceResult(
                        senders.get(senderIdx).getKey().getId(),
                        senders.get(senderIdx).getKey().getName(),
                        receivers.get(receiverIdx).getValue().abs(),
                        receivers.get(receiverIdx).getKey().getId(),
                        receivers.get(receiverIdx).getKey().getName()
                ));
                receivers.get(receiverIdx).setValue(amountToTransfer);
                senders.get(senderIdx).setValue(BigDecimal.ZERO);
                senderIdx++;
            } else {
                balanceResults.add(new BalanceResult(
                        senders.get(senderIdx).getKey().getId(),
                        senders.get(senderIdx).getKey().getName(),
                        receivers.get(receiverIdx).getValue().abs(),
                        receivers.get(receiverIdx).getKey().getId(),
                        receivers.get(receiverIdx).getKey().getName()
                ));
                receivers.get(receiverIdx).setValue(BigDecimal.ZERO);
                senders.get(senderIdx).setValue(BigDecimal.ZERO);
                receiverIdx++;
                senderIdx++;
            }
        }

        return balanceResults;
    }
}
