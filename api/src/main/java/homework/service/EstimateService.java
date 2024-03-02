package homework.service;

import homework.dto.EstimateRequest;
import homework.dto.EstimateResponse;
import homework.payment.dto.MerchantFeeDto;
import homework.user.entity.User;
import homework.user.repository.MerchantQueryRepository;
import homework.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EstimateService {
    private final UserQueryRepository userQueryRepository;
    private final MerchantQueryRepository merchantQueryRepository;

    public EstimateResponse estimate(EstimateRequest estimateRequest) {
        User foundUser = this.userQueryRepository.findUserWithAccountUserId(estimateRequest.getUserId());
        MerchantFeeDto feeDto = this.merchantQueryRepository.findMerchantFeeDtoByMerchantIdAndCurrency(estimateRequest.getDestination(), estimateRequest.getCurrency());

        BigDecimal fees = feeDto == null ? null : feeDto.getFee();
        BigDecimal estimatedTotal = foundUser.estimateAccountPayment(estimateRequest.getAmount(), fees, estimateRequest.getCurrency());
        return EstimateResponse.builder()
                .estimatedTotal(estimatedTotal)
                .fees(fees)
                .currency(foundUser.getAccountCurrency())
                .build();
    }
}