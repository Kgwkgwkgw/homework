package homework.payment.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MerchantFeeDto {
    private BigDecimal fee;
}
