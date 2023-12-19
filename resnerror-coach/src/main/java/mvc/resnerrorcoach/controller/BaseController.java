package mvc.resnerrorcoach.controller;

import jakarta.servlet.http.HttpServletResponse;
import mvc.resnerrorcoach.common.ApiResponse;
import mvc.resnerrorcoach.common.ExceptionContext;
import mvc.resnerrorcoach.exception.CustomException;
import mvc.resnerrorcoach.exception.CustomExceptionV2;
import org.springframework.boot.context.annotation.Configurations;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.module.Configuration;
import java.util.*;

public class BaseController {

    public <T> ApiResponse<T> makeResponse(T result) {
        return makeResponse(Collections.singletonList(result));
    }

    public <T> ApiResponse<T> makeResponse(List<T> result) {
        return new ApiResponse<>(result);
    }

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Integer> customExceptionHandler(
            HttpServletResponse response,
            CustomException e
    ) {
        response.setStatus(e.getErrorCode().getHttpStatus().value());

        return new ApiResponse<>(e.getErrorCode().getCode(), e.getMessage(), e.getValue());
    }

    @ExceptionHandler(CustomExceptionV2.class)
    public ApiResponse<Integer> customExceptionV2Handler(
            HttpServletResponse response,
            CustomExceptionV2 e
    ) {
        response.setStatus(e.getErrorCode().getHttpStatus().value());

        return new ApiResponse<>(e.getErrorCode().getCode(),
                e.getErrorCode().getMessage(),
                ExceptionContext.threadLocal.get());
    }
}
