package homework.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.dto.UserEmailDto;
import homework.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import homework.dto.UserWithAccountDto;

import static homework.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public UserWithAccountDto findUserWithAccountDtoByUserId(Long userId) {
        return this.jpaQueryFactory
                .select(Projections.fields(UserWithAccountDto.class,
                        user.id.as("userId"),
                        user.account.balance,
                        user.account.currency))
                .from(user)
                .leftJoin(user.account)
                .where(user.id.eq(userId)).fetchOne();
    }

    public UserEmailDto findUserEmailDtoByUserId(Long userId) {
        return this.jpaQueryFactory
                .select(Projections.fields(UserEmailDto.class,
                        user.email))
                .from(user)
                .where(user.id.eq(userId)).fetchOne();
    }

    public User findUserWithAccountUserId(Long userId) {
        return this.jpaQueryFactory.selectFrom(user)
                .innerJoin(user.account)
                .fetchJoin().where(user.id.eq(userId)).fetchOne();
    }
}
