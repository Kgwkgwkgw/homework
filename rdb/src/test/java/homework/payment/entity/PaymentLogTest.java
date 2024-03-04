package homework.payment.entity;

import homework.enums.TransactionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PaymentLogTest {
    @DisplayName("결제에 성공하면, 거래 상태가 APPROVED 상태이고, 결제 승인 시간이 업데이트됩니다.")
    @Test
    public void checkApproved() {
        PaymentLog paymentLog = PaymentLog.builder()
                .build();
        TransactionStatus originStatus = paymentLog.getTransactionStatus();
        LocalDateTime now = LocalDateTime.now();

        paymentLog.process(TransactionStatus.APPROVED, now);

        Assertions.assertEquals(TransactionStatus.READY, originStatus);
        Assertions.assertEquals(TransactionStatus.APPROVED, paymentLog.getTransactionStatus());
        Assertions.assertEquals(now, paymentLog.getApprovedAt());
    }

    @DisplayName("결제에 실패하면, 거래 상태가 FAILED 상태이고, 마지막 실패 시간이 업데이트됩니다.")
    @Test
    public void checkFailed() {
        PaymentLog paymentLog = PaymentLog.builder()
                .build();
        TransactionStatus originStatus = paymentLog.getTransactionStatus();
        LocalDateTime now = LocalDateTime.now();

        paymentLog.process(TransactionStatus.FAILED, now);

        Assertions.assertEquals(TransactionStatus.READY, originStatus);
        Assertions.assertEquals(TransactionStatus.FAILED, paymentLog.getTransactionStatus());
        Assertions.assertEquals(now, paymentLog.getLastFailedAt());
    }
}