package mvc.resnerrorcoach.exception;

import lombok.Getter;
import mvc.resnerrorcoach.common.ErrorCode;
import mvc.resnerrorcoach.common.ExceptionContext;

@Getter
public class CustomExceptionV2 extends RuntimeException{

    private final ErrorCode errorCode;
    private String message;

    public CustomExceptionV2(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomExceptionV2(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public CustomExceptionV2(ErrorCode errorCode, String message, int grade) {
        this.errorCode = errorCode;
        this.message = message;
        ExceptionContext.threadLocal.get().add(grade);
    }

}
