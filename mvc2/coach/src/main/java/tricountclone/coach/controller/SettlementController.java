package tricountclone.coach.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tricountclone.coach.model.BalanceResult;
import tricountclone.coach.model.Settlement;
import tricountclone.coach.service.SettlementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settles")
public class SettlementController {

    private final SettlementService settlementService;

    @PostMapping("/create")
    public ResponseEntity<Settlement> createSettlement(@RequestParam String settlementName) {
        return new ResponseEntity<>(settlementService.createSettlement(settlementName), HttpStatus.OK);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Void> joinSettlement(@PathVariable("id") Long settlementId) {
        settlementService.joinSettlement(settlementId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<List<BalanceResult>> getSettlementBalanceResult(@PathVariable("id") Long settlementId) {
        return new ResponseEntity<>(settlementService.getBalanceResult(settlementId), HttpStatus.OK);
    }
}
