package homework.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.enums.CurrencyType;
import homework.payment.dto.MerchantFeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static homework.payment.entity.QMerchantFee.merchantFee;

@Repository
@RequiredArgsConstructor
public class MerchantQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MerchantFeeDto findMerchantFeeDtoByMerchantIdAndCurrency(String merchantId, CurrencyType currency) {
        return this.jpaQueryFactory
                .select(Projections.fields(MerchantFeeDto.class, merchantFee.fees))
                .from(merchantFee)
                .innerJoin(merchantFee.merchant)
                .where(merchantFee.merchant.id.eq(merchantId).and(merchantFee.currency.eq(currency)))
                .fetchOne();
    }
}
