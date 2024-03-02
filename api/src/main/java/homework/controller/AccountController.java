package homework.controller;


import homework.dto.AccountByUserIdReadResponse;
import homework.user.dto.UserWithAccountDto;
import homework.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserQueryRepository userQueryRepository;

    @GetMapping("/api/payment/balance/{userId:\\d+}")
    public AccountByUserIdReadResponse readAccountByUserId(@PathVariable Long userId) {
        UserWithAccountDto dto = this.userQueryRepository.findUserWithAccountDtoByUserId(userId);
        return new AccountByUserIdReadResponse(dto);
    }
}
