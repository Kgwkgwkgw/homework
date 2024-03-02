package homework.dto;

import homework.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class EstimateRequest {
    @NotNull
    private BigDecimal amount;
    @NotNull
    private CurrencyType currency;
    @NotNull
    private String destination;
    @NotNull
    private Long userId;
}
