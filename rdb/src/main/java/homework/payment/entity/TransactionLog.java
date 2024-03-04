package homework.payment.entity;

import homework.enums.CurrencyType;
import homework.enums.TransactionStatus;
import homework.payment.repository.PaymentLogRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_log_id")
    private PaymentLog paymentLog;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private LocalDateTime transactionAt;

    public TransactionLog(PaymentLog paymentLog) {
        this.paymentLog = paymentLog;
        this.amount = paymentLog.getAmount();
        this.transactionStatus = paymentLog.getTransactionStatus();
        this.transactionAt = paymentLog.getLastTransactionAt();
    }
}
