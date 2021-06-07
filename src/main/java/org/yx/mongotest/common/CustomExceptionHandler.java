package org.yx.mongotest.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangxin
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Throwable e) {
        e.printStackTrace();
        return Result.failed(e.getMessage());
    }
}
