package homework.dto;

import homework.enums.TransactionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PaymentResponse {
    private TransactionStatus status;
    private LocalDateTime transactionAt;

    @Builder
    public PaymentResponse(TransactionStatus status, LocalDateTime transactionAt) {
        this.status = status;
        this.transactionAt = transactionAt;
    }


}
