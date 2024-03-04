package homework.factory;


import homework.enums.PaymentMethod;
import homework.infra.ApproveCardPaymentService;
import homework.infra.ApproveSimplePayPaymentService;
import homework.service.ApprovePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMethodServiceFactory {
    private final ApproveCardPaymentService approveCardPaymentService;
    private final ApproveSimplePayPaymentService approveSimplePayPaymentService;

    public ApprovePaymentService getApproveServiceByPaymentMethodType(PaymentMethod paymentMethod) {
        if (PaymentMethod.CARD.equals(paymentMethod)) {
            return approveCardPaymentService;
        }
        if (PaymentMethod.SIMPLE_PAYMENT.equals(paymentMethod)) {
            return approveSimplePayPaymentService;
        }
        throw new UnsupportedOperationException();
    }
}
