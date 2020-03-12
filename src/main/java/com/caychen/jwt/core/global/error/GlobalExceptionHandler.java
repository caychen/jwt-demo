package com.caychen.jwt.core.global.error;

import com.caychen.jwt.core.error.CustomException;
import com.caychen.jwt.core.error.GlobalCode;
import com.caychen.jwt.core.global.result.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler  {

    @ExceptionHandler(CustomException.class)
    public ResponseResult handleException(Exception e){
        return ResponseResult.error(GlobalCode.SYSTEM_INTERNAL_ERROR, e.getMessage());
    }
}
