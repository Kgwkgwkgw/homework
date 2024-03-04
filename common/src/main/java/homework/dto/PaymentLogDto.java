package homework.dto;

import homework.enums.CurrencyType;
import homework.enums.PaymentMethod;
import homework.enums.TransactionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PaymentLogDto {
    private String id;
    private TransactionStatus transactionStatus;
    private String merchantId;
    private BigDecimal amount;
    private CurrencyType currency;
    private BigDecimal balanceAmount;
    private PaymentMethod paymentMethod;
    private PaymentLogExtra extra;
    private LocalDateTime approvedAt;
    private LocalDateTime lastTransactionAt;
    private LocalDateTime lastCanceledAt;
    private LocalDateTime lastFailedAt;
    private int failedCnt;
    private LocalDateTime requestAt;

    @Builder
    public PaymentLogDto(String id, TransactionStatus transactionStatus, String merchantId, BigDecimal amount, CurrencyType currency, BigDecimal balanceAmount,
                         PaymentMethod paymentMethod, PaymentLogExtra extra, LocalDateTime approvedAt, LocalDateTime lastTransactionAt, LocalDateTime lastCanceledAt, LocalDateTime lastFailedAt, int failedCnt, LocalDateTime requestAt) {
        this.id = id;
        this.transactionStatus = transactionStatus;
        this.merchantId = merchantId;
        this.amount = amount;
        this.currency = currency;
        this.balanceAmount = balanceAmount;
        this.paymentMethod = paymentMethod;
        this.extra = extra;
        this.approvedAt = approvedAt;
        this.lastTransactionAt = lastTransactionAt;
        this.lastCanceledAt = lastCanceledAt;
        this.lastFailedAt = lastFailedAt;
        this.failedCnt = failedCnt;
        this.requestAt = requestAt;
    }

}
