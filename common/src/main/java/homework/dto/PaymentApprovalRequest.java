package homework.dto;

import homework.enums.CurrencyType;
import homework.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentApprovalRequest {
    @NotNull
    private Long userId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private CurrencyType currency;
    @NotNull
    private String merchantId;
    @NotNull
    private PaymentMethod paymentMethod;
    @NotNull
    private PaymentLogExtra paymentDetails;
    public PaymentRequest toPaymentRequest(String paymentId) {
        return PaymentRequest.builder()
                .paymentId(paymentId)
                .amount(this.amount)
                .currency(this.currency)
                .paymentMethod(this.paymentMethod)
                .paymentDetails(new PaymentLogExtra(paymentDetails))
                .build();
    }
}
