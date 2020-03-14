package com.caychen.jwt.core.global.error;

import com.caychen.jwt.core.error.CustomException;
import com.caychen.jwt.core.error.GlobalCode;
import com.caychen.jwt.core.global.result.ResponseResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        return ResponseResult.error(GlobalCode.SYSTEM_INTERNAL_ERROR, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseResult handleException(NullPointerException e) {
        return ResponseResult.error(GlobalCode.NULL_POINTER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult handleException(CustomException e) {
        return ResponseResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        return ResponseResult.error(GlobalCode.INVALID_PARAM_ERROR, allErrors.get(0).getDefaultMessage());
    }
}
