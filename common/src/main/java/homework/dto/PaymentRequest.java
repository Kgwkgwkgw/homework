package homework.dto;

import homework.enums.CurrencyType;
import homework.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;


import java.math.BigDecimal;


@Getter
public class PaymentRequest {
    private String paymentId;
    private BigDecimal amount;
    private CurrencyType currency;
    private PaymentMethod paymentMethod;
    private PaymentLogExtra paymentDetails;

    @Builder
    public PaymentRequest(String paymentId, BigDecimal amount, CurrencyType currency, PaymentMethod paymentMethod, PaymentLogExtra paymentDetails) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
    }
}
