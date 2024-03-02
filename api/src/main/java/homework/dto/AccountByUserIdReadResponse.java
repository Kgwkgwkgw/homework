package homework.dto;


import homework.enums.CurrencyType;
import homework.user.dto.UserWithAccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class AccountByUserIdReadResponse {
    private Long userId;
    private BigDecimal balance;
    private CurrencyType currency;

    public AccountByUserIdReadResponse(UserWithAccountDto userWithAccountDto) {
        this.userId = userWithAccountDto.getUserId();
        this.balance = userWithAccountDto.getBalance();
        this.currency = userWithAccountDto.getCurrency();
    }
}
