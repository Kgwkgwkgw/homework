package homework.infra;

import homework.dto.PaymentRequest;
import homework.dto.PaymentResponse;
import homework.enums.TransactionStatus;
import homework.service.ApprovePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApproveSimplePayPaymentService implements ApprovePaymentService {

    public Mono<PaymentResponse> request(PaymentRequest paymentRequest) {
        // http request..................
        return Mono.just(PaymentResponse.builder()
                .status(TransactionStatus.APPROVED)
                .transactionAt(LocalDateTime.now())
                .build());
    }
}
