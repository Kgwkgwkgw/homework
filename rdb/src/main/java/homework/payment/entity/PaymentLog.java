package homework.payment.entity;

import homework.dto.PaymentLogExtra;
import homework.enums.CurrencyType;
import homework.enums.PaymentMethod;
import homework.enums.TransactionStatus;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static homework.enums.TransactionStatus.APPROVED;
import static homework.enums.TransactionStatus.FAILED;

@Entity
@Table
@Getter
@NoArgsConstructor
public class PaymentLog {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private BigDecimal balanceAmount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private PaymentLogExtra extra;
    private LocalDateTime approvedAt;
    private LocalDateTime lastTransactionAt;
    private LocalDateTime lastCanceledAt;
    private LocalDateTime lastFailedAt;
    private int failedCnt;
    private LocalDateTime requestAt;

    @Builder
    public PaymentLog(BigDecimal amount, CurrencyType currency, PaymentMethod paymentMethod, PaymentLogExtra extra, LocalDateTime requestAt, Long userId, Merchant merchant) {
        this.id = String.valueOf(UUID.randomUUID());
        this.transactionStatus = TransactionStatus.READY;
        this.merchant = merchant;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.balanceAmount = amount;
        this.paymentMethod = paymentMethod;
        this.extra = extra;
        this.requestAt = requestAt;
    }

    public void process(TransactionStatus paymentResult, LocalDateTime transactionAt) {
        this.transactionStatus = paymentResult;
        this.lastTransactionAt = transactionAt;

        if (APPROVED.equals(paymentResult)) {
            this.approvedAt = transactionAt;
        }
        if (FAILED.equals(paymentResult)) {
            this.failedCnt++;
            this.lastFailedAt = transactionAt;
        }

    }
}
