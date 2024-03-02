package homework.controller;

import homework.dto.ApiErrorResult;
import homework.enums.ResponseErrorCode;
import homework.exceptions.InsufficientBalanceException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class ControllerAdvice {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ApiErrorResult<>(ResponseErrorCode.ILLEGAL_ARGUMENT), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException(InsufficientBalanceException e) {
        return new ResponseEntity<>(new ApiErrorResult<>(ResponseErrorCode.IN_SUFFICIENT_BALANCE), HttpStatus.BAD_REQUEST);
    }
}
