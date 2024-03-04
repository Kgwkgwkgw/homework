package homework.infra;

import homework.dto.PaymentRequest;
import homework.dto.PaymentResponse;
import homework.enums.TransactionStatus;
import homework.service.ApprovePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApproveCardPaymentService implements ApprovePaymentService {
    private final WebClient webclient;

    public Mono<PaymentResponse> request(PaymentRequest paymentRequest) {
        // http request..................
        return Mono.just(PaymentResponse.builder()
                .status(TransactionStatus.APPROVED)
                .transactionAt(LocalDateTime.now())
                .build());
    }
}
