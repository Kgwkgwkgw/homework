package homework.payment.entity;

import homework.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class MerchantFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private BigDecimal fees;
    private LocalDateTime createdAt;
}
