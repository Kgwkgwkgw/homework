package homework.user.entity;


import homework.enums.CurrencyType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "uk_user_email", columnNames = {"email"})})
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToOne(mappedBy = "user")
    private Account account;
    private LocalDateTime createdAt;

    public BigDecimal estimateAccountPayment(BigDecimal amountOfPayment, @Nullable BigDecimal fee, CurrencyType paymentCurrency) {
        return this.account.estimatePayment(amountOfPayment, fee, paymentCurrency);
    }

    public CurrencyType getAccountCurrency() {
        if (this.account == null) {
            throw new IllegalArgumentException();
        }
        return this.account.getCurrency();
    }
}
