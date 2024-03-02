package homework.user.entity;

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
}
