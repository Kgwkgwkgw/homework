package homework.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CardPaymentDetail {
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvv;
}
