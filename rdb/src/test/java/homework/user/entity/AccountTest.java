package homework.user.entity;

import homework.enums.CurrencyType;
import homework.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountTest {

    @Test
    @DisplayName("결제 통화와 계좌 통화가 다르면 IllegalArgument 예외가 발생합니다.")
    public void estimateFailedCase1() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.USD)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.estimatePayment(new BigDecimal(10), null, CurrencyType.KRW);
        });
    }

    @Test
    @DisplayName("결제 금액이 0원이거나 음수라면 IllegalArgument 예외가 발생합니다.")
    public void estimateFailedCase2() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.estimatePayment(BigDecimal.ZERO, null, CurrencyType.KRW);
        });
    }

    @Test
    @DisplayName("결제 금액이 0원이거나 음수라면 IllegalArgument 예외가 발생합니다.")
    public void estimateFailedCase3() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.estimatePayment(new BigDecimal(-1), null, CurrencyType.KRW);
        });
    }

    @Test
    @DisplayName("결제 통화와 계좌 통화가 다르면 IllegalArgument 예외가 발생합니다.")
    public void estimateFailedCase4() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.estimatePayment(new BigDecimal(10), null, CurrencyType.USD);
        });
    }

    @Test
    @DisplayName("예상 결제 결과 잔고가 0보다 작으면, InsufficientBalance 예외가 발생합니다.")
    public void estimateFailedCase5() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            account.estimatePayment(new BigDecimal(1_001), null, CurrencyType.KRW);
        });
    }

    @Test
    @DisplayName("예상 결제 결과 잔고가 0보다 작으면, InsufficientBalance 예외가 발생합니다.")
    public void estimateFailedCase6() {
        Account account = Account.builder()
                .balance(new BigDecimal(1_000))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            account.estimatePayment(new BigDecimal(1_000), BigDecimal.ONE, CurrencyType.KRW);
        });
    }

    @Test
    @DisplayName("예상 결제 결과 잔고가 0보다 작으면, InsufficientBalance 예외가 발생합니다.")
    public void estimateFailedCase7() {
        Account account = Account.builder()
                .balance(new BigDecimal("100.005"))
                .currency(CurrencyType.USD)
                .createdAt(LocalDateTime.now())
                .build();
        Assertions.assertThrows(InsufficientBalanceException.class, () -> {
            account.estimatePayment(new BigDecimal("100"), new BigDecimal("0.006"), CurrencyType.USD);
        });
    }

    @Test
    @DisplayName("원화 정상 결제")
    public void estimateSucceededCase1() {
        Account account = Account.builder()
                .balance(new BigDecimal("1000"))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        BigDecimal estimatedBalance = account.estimatePayment(new BigDecimal("900"), new BigDecimal("100"), CurrencyType.KRW);
        Assertions.assertEquals(0, BigDecimal.ZERO.compareTo(estimatedBalance));
    }

    @Test
    @DisplayName("원화 정상 결제")
    public void estimateSucceededCase2() {
        Account account = Account.builder()
                .balance(new BigDecimal("1000"))
                .currency(CurrencyType.KRW)
                .createdAt(LocalDateTime.now())
                .build();
        BigDecimal estimatedBalance = account.estimatePayment(new BigDecimal("800"), new BigDecimal("100"), CurrencyType.KRW);
        Assertions.assertEquals(0, new BigDecimal("100").compareTo(estimatedBalance));
    }

    @Test
    @DisplayName("달러 정상 결제")
    public void estimateSucceededCase3() {
        Account account = Account.builder()
                .balance(new BigDecimal("1000.5"))
                .currency(CurrencyType.USD)
                .createdAt(LocalDateTime.now())
                .build();
        BigDecimal estimatedBalance = account.estimatePayment(new BigDecimal("1000"), new BigDecimal("0.5"), CurrencyType.USD);
        Assertions.assertEquals(0, BigDecimal.ZERO.compareTo(estimatedBalance));
    }

    @Test
    @DisplayName("달러 정상 결제")
    public void estimateSucceededCase4() {
        Account account = Account.builder()
                .balance(new BigDecimal("1000.5"))
                .currency(CurrencyType.USD)
                .createdAt(LocalDateTime.now())
                .build();
        BigDecimal estimatedBalance = account.estimatePayment(new BigDecimal("1000"), new BigDecimal("0.333333"), CurrencyType.USD);
        Assertions.assertEquals(0, new BigDecimal("0.166667").compareTo(estimatedBalance));
    }
}