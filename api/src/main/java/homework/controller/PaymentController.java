package homework.controller;

import homework.dto.ApprovePaymentResponse;
import homework.dto.PaymentApprovalRequest;
import homework.service.ApproveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final ApproveService approveService;

    @PostMapping("/api/payment/approval")
    public Mono<ApprovePaymentResponse> approve(@RequestBody @Valid PaymentApprovalRequest request) {
        return this.approveService.approve(request);
    }
}
