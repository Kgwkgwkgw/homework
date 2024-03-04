package homework.payment.service;

import homework.dto.PaymentLogExtra;
import homework.dto.PaymentResponse;
import homework.enums.CurrencyType;
import homework.enums.PaymentMethod;
import homework.enums.TransactionStatus;
import homework.exceptions.NotFoundException;
import homework.dto.PaymentLogDto;
import homework.payment.entity.PaymentLog;
import homework.payment.entity.TransactionLog;
import homework.payment.repository.PaymentLogRepository;
import homework.payment.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProcessPaymentLogService {
    private final PaymentLogRepository paymentLogRepository;
    private final TransactionLogRepository transactionLogRepository;

    @Transactional
    public PaymentLogDto process(String readPaymentLogId, PaymentResponse paymentResponse) {
        PaymentLog paymentLog = this.paymentLogRepository.findById(readPaymentLogId).orElseThrow(NotFoundException::new);
        paymentLog.process(paymentResponse.getStatus(), paymentResponse.getTransactionAt());
        TransactionLog transactionLog = new TransactionLog(paymentLog);
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
