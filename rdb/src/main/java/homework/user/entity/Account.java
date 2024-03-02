package homework.user.entity;

import homework.enums.CurrencyType;
import homework.exceptions.InsufficientBalanceException;
import homework.payment.dto.MerchantFeeDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_account_user_id", columnNames = "user_id"), @UniqueConstraint(name = "uk_account_account_number", columnNames = "account_number")})
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(precision = 38, scale = 5)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private String accountNumber;
    private LocalDateTime createdAt;

    @Builder
    public Account(BigDecimal balance, CurrencyType currency, LocalDateTime createdAt) {
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
    }

    public BigDecimal estimatePayment(BigDecimal amountToPay, @Nullable BigDecimal fee, CurrencyType currency) {
        BigDecimal toPayTotalAmount = getTotalAmountToPay(amountToPay, fee);
        return this.getEstimatedBalance(toPayTotalAmount, currency);
    }

    private BigDecimal getTotalAmountToPay(BigDecimal toPayAmount, @Nullable BigDecimal fee) {
        if (fee != null) {
            return toPayAmount.add(fee);
        }
        return toPayAmount;
    }

    private BigDecimal getEstimatedBalance(BigDecimal totalAmountToPay, CurrencyType currency) {
        if (!this.currency.equals(currency)) {
            throw new IllegalArgumentException();
        }
        if (totalAmountToPay.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal remainingBalance = this.balance.subtract(totalAmountToPay);

        if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException();
        }
        return remainingBalance;
    }
}
