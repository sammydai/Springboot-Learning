package com.learning.apidesign.response;

import java.lang.annotation.*;

/**
 * @Package: com.learning.apidesign.response
 * @Description: NoAPIResponse
 * @Author: Sammy
 * @Date: 2020/12/21 21:55
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAPIResponse {

}
