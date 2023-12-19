package mvc.resnerrorcoach.exception;

import lombok.Getter;
import mvc.resnerrorcoach.common.ErrorCode;
import org.springframework.core.codec.CodecException;

@Getter
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    private String message;

    private int value = 0;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomException(ErrorCode errorCode, String message, int grade) {
        this.errorCode = errorCode;
        this.message = message;
        this.value = grade;
    }
}
