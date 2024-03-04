package homework.dto;

import homework.enums.CurrencyType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class EstimateResponse {
    private BigDecimal estimatedTotal;
    private BigDecimal fees;
    private CurrencyType currency;

    @Builder
    public EstimateResponse(BigDecimal estimatedTotal, BigDecimal fees, CurrencyType currency) {
        this.estimatedTotal = estimatedTotal;
        this.fees = fees;
        this.currency = currency;
    }
}
