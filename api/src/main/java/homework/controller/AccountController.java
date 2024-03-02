package homework.controller;


import homework.dto.AccountByUserIdReadResponse;
import homework.dto.EstimateRequest;
import homework.dto.EstimateResponse;
import homework.service.EstimateService;
import homework.user.dto.UserWithAccountDto;
import homework.user.repository.UserQueryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final UserQueryRepository userQueryRepository;
    private final EstimateService estimateService;

    @GetMapping("/api/payment/balance/{userId:\\d+}")
    public AccountByUserIdReadResponse readAccountByUserId(@PathVariable Long userId) {
        UserWithAccountDto dto = this.userQueryRepository.findUserWithAccountDtoByUserId(userId);
        return new AccountByUserIdReadResponse(dto);
    }

    @PostMapping("/api/payment/estimate")
    public EstimateResponse estimate(@RequestBody @Valid EstimateRequest estimateRequest) {
        return this.estimateService.estimate(estimateRequest);
    }
}
