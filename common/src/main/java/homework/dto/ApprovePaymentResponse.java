package homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import homework.enums.CurrencyType;
import homework.enums.TransactionStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Getter
public class ApprovePaymentResponse {
    private String paymentId;
    private TransactionStatus status;
    private BigDecimal amount;
    private CurrencyType currency;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private OffsetDateTime timestamp;


    public ApprovePaymentResponse(PaymentLogDto paymentLogDto) {
        this.paymentId = paymentLogDto.getId();
        this.status = paymentLogDto.getTransactionStatus();
        this.amount = paymentLogDto.getAmount();
        this.currency = paymentLogDto.getCurrency();
        this.timestamp = paymentLogDto.getLastTransactionAt().atOffset(ZoneOffset.UTC);
    }
}
