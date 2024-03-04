package homework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PaymentLogExtra {
    private Card card;
    private VirtualAccount virtualAccount;

    public PaymentLogExtra(PaymentLogExtra paymentLogExtra) {
        this.card = new Card(card);
        this.virtualAccount = new VirtualAccount(virtualAccount);
    }
    @Builder
    public PaymentLogExtra(Card card, VirtualAccount virtualAccount) {
        this.card = new Card(card);
        this.virtualAccount = new VirtualAccount(virtualAccount);
    }

    @Getter
    @NoArgsConstructor
    public static class Card {
        private String cardNumber;
        private LocalDate expiryDate;
        private String cvv;

        public Card(Card card) {
            if (card != null) {
                this.cardNumber = card.getCardNumber();
                this.expiryDate = card.getExpiryDate();
                this.cvv = card.getCvv();
            }
        }
    }

    @Getter
    @NoArgsConstructor
    public static class VirtualAccount {
        private String accountNumber;
        private String bankCode;

        public VirtualAccount(VirtualAccount virtualAccount) {
            if (virtualAccount != null) {
                this.accountNumber = virtualAccount.getAccountNumber();
                this.bankCode = virtualAccount.getBankCode();
            }
        }
    }
}
