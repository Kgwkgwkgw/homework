package homework.payment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.enums.CurrencyType;
import homework.dto.MerchantFeeDto;
import homework.payment.entity.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static homework.payment.entity.QMerchant.merchant;
import static homework.payment.entity.QMerchantFee.merchantFee;

@Repository
@RequiredArgsConstructor
public class MerchantQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public Merchant findMerchantByMerchantId(String merchantId) {
        return this.jpaQueryFactory
                .selectFrom(merchant)
                .where(merchant.id.eq(merchantId))
                .fetchOne();
    }

    public MerchantFeeDto findMerchantFeeDtoByMerchantIdAndCurrency(String merchantId, CurrencyType currency) {
        return this.jpaQueryFactory
                .select(Projections.fields(MerchantFeeDto.class, merchantFee.fees))
                .from(merchantFee)
                .innerJoin(merchantFee.merchant)
                .where(merchantFee.merchant.id.eq(merchantId).and(merchantFee.currency.eq(currency)))
                .fetchOne();
    }
}
