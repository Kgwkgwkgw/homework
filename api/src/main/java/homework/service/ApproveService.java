package homework.service;

import homework.dto.*;
import homework.factory.PaymentMethodServiceFactory;
import homework.payment.service.PreparePaymentLogService;
import homework.payment.service.ProcessPaymentLogService;
import homework.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ApproveService {
    private final PreparePaymentLogService preparePaymentLogService;
    private final ProcessPaymentLogService processPaymentLogService;
    private final PaymentMethodServiceFactory paymentMethodServiceFactory;
    private final UserQueryRepository userQueryRepository;
    private final SendEmailService sendEmailService;

    public Mono<ApprovePaymentResponse> approve(PaymentApprovalRequest request) {
        UserEmailDto foundUser = this.userQueryRepository.findUserEmailDtoByUserId(request.getUserId());
        if (foundUser == null) {
            throw new IllegalArgumentException();
        }
        // 결제 요청 데이터를 저장함
        PaymentLogDto readyLog = this.preparePaymentLogService.prepare(request);
        // 지불 타입에 따라 서비스를 획득함
        ApprovePaymentService paymentService = paymentMethodServiceFactory.getApproveServiceByPaymentMethodType(request.getPaymentMethod());
        // 지불 타입별로 승인 요청 API를 호출하고 그 결과를 이용하여 다시 결과를 처리함
        Mono<PaymentResponse> res = paymentService.request(request.toPaymentRequest(readyLog.getId()));
        return res
                .publishOn(Schedulers.boundedElastic())
                .map(r -> {
                    // 결제 로그 결과 처리
                    PaymentLogDto paymentLogAfterProcessing = this.processPaymentLogService.process(readyLog.getId(), r);
                    // 이메일 전송
                    this.sendEmailService.sendEmail(foundUser.getEmail(), "결제 완료", "결제 완료됐습니다.");
                    return new ApprovePaymentResponse(paymentLogAfterProcessing);
                });
    }
}
