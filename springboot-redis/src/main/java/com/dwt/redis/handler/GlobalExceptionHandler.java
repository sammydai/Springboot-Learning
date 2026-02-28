package com.dwt.redis.handler;


import com.dwt.redis.dto.Result;
import com.dwt.redis.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleBusinessException(RuntimeException e, HttpServletRequest request) {
        log.error("业务异常: {}", e.getMessage());
        return Result.error(ResultCode.BUSINESS_ERROR.getCode(), e.getMessage());
    }
    
    /**
     * 处理参数校验异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<?> handleValidationException(Exception e) {
        log.error("参数校验异常: {}", e.getMessage());
        return Result.error(ResultCode.PARAM_IS_INVALID.getCode(), "参数校验失败");
    }
    
    /**
     * 处理路径不存在异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handleNotFoundException(NoHandlerFoundException e) {
        log.error("请求路径不存在: {}", e.getMessage());
        return Result.error("404", "请求路径不存在");
    }
    
    /**
     * 处理所有未知异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleGlobalException(Exception e, HttpServletRequest request) {
        log.error("系统异常: ", e);
        return Result.error(ResultCode.SYSTEM_ERROR.getCode(), 
                          ResultCode.SYSTEM_ERROR.getMessage());
    }
    
    /**
     * 处理限流异常
     */
    @ExceptionHandler(RateLimitException.class)
    public Result<?> handleRateLimitException(RateLimitException e) {
        log.warn("限流异常: {}", e.getMessage());
        return Result.error(ResultCode.RATE_LIMIT_ERROR.getCode(),
                          ResultCode.RATE_LIMIT_ERROR.getMessage());
    }
}

// 自定义限流异常类
class RateLimitException extends RuntimeException {
    public RateLimitException(String message) {
        super(message);
    }
}