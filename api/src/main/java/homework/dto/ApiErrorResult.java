package homework.dto;


import homework.enums.ResponseErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorResult<T> {
    private ResponseErrorCode errorCode;
    private Map<String, T> extras;

    public ApiErrorResult(ResponseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ApiErrorResult(ResponseErrorCode errorCode, Map<String, T> extras) {
        this.errorCode = errorCode;
        this.extras = extras;
    }
}
