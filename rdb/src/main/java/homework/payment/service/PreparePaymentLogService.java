package homework.payment.service;

import homework.dto.PaymentApprovalRequest;
import homework.dto.PaymentLogDto;
import homework.payment.entity.Merchant;
import homework.payment.entity.PaymentLog;
import homework.payment.entity.TransactionLog;
import homework.payment.repository.MerchantQueryRepository;
import homework.payment.repository.PaymentLogRepository;
import homework.payment.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PreparePaymentLogService {
    private final PaymentLogRepository paymentLogRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final MerchantQueryRepository merchantQueryRepository;
    @Transactional
    public PaymentLogDto prepare (PaymentApprovalRequest request) {
        Merchant foundMerchant = this.merchantQueryRepository.findMerchantByMerchantId(request.getMerchantId());
        if (foundMerchant == null) {
            throw new IllegalArgumentException();
        }

        PaymentLog paymentLog = PaymentLog.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .extra(request.getPaymentDetails())
                .merchant(foundMerchant)
                .currency(request.getCurrency())
                .requestAt(LocalDateTime.now())
                .userId(request.getUserId())
                .build();
        TransactionLog transactionLog = new TransactionLog(paymentLog);
        this.paymentLogRepository.save(paymentLog);
        this.transactionLogRepository.save(transactionLog);
        return PaymentLogDto.builder()
                .id(paymentLog.getId())
                .transactionStatus(paymentLog.getTransactionStatus())
                .merchantId(paymentLog.getMerchant().getId())
                .amount(paymentLog.getAmount())
                .currency(paymentLog.getCurrency())
                .balanceAmount(paymentLog.getBalanceAmount())
                .paymentMethod(paymentLog.getPaymentMethod())
                .extra(paymentLog.getExtra())
                .approvedAt(paymentLog.getApprovedAt())
                .lastTransactionAt(paymentLog.getLastTransactionAt())
                .lastCanceledAt(paymentLog.getLastCanceledAt())
                .lastFailedAt(paymentLog.getLastFailedAt())
                .failedCnt(paymentLog.getFailedCnt())
                .requestAt(paymentLog.getRequestAt())
                .build();

    }
}
