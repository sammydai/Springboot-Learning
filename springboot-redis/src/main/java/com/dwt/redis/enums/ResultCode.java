package com.dwt.redis.enums;

public enum ResultCode {
    // 成功状态码
    SUCCESS("200", "操作成功"),
    
    // 业务错误码
    SECKILL_SUCCESS("200001", "秒杀成功"),
    SECKILL_END("200002", "秒杀结束"),
    SECKILL_REPEAT("200003", "重复秒杀"),
    SECKILL_WAIT("200004", "排队中"),
    
    // 参数错误
    PARAM_IS_INVALID("400001", "参数无效"),
    PARAM_IS_BLANK("400002", "参数为空"),
    PARAM_TYPE_BIND_ERROR("400003", "参数类型错误"),
    PARAM_NOT_COMPLETE("400004", "参数缺失"),
    
    // 用户错误
    USER_NOT_LOGGED_IN("401001", "用户未登录"),
    USER_LOGIN_ERROR("401002", "账号或密码错误"),
    USER_ACCOUNT_FORBIDDEN("401003", "账号已被禁用"),
    USER_NOT_EXIST("401004", "用户不存在"),
    USER_HAS_EXIST("401005", "用户已存在"),
    
    // 业务错误
    BUSINESS_ERROR("402001", "业务逻辑错误"),
    STOCK_NOT_ENOUGH("402002", "库存不足"),
    ORDER_NOT_EXIST("402003", "订单不存在"),
    ORDER_EXPIRED("402004", "订单已过期"),
    
    // 系统错误
    SYSTEM_ERROR("500001", "系统异常，请稍后重试"),
    SERVICE_UNAVAILABLE("500002", "服务不可用"),
    RATE_LIMIT_ERROR("500003", "请求过于频繁"),
    
    // 接口错误
    INTERFACE_ERROR("600001", "内部接口调用异常"),

    FAIL_DEF("909091","自定义错误");

    private String code;
    private String message;
    
    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}