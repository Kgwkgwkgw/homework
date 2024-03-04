package homework.dto;

import homework.enums.CurrencyType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserWithAccountDto {
    private Long userId;
    private BigDecimal balance;
    private CurrencyType currency;
}
