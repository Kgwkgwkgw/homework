package homework.service;

import homework.dto.PaymentRequest;
import homework.dto.PaymentResponse;
import reactor.core.publisher.Mono;

public interface ApprovePaymentService {
    public Mono<PaymentResponse> request(PaymentRequest paymentRequest);
}
