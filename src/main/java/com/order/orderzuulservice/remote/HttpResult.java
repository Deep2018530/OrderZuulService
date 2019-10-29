package com.order.orderzuulservice.remote;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by zhangdingping at 2019/10/23
 * 控制器响应结果集
 */
@Data
@NoArgsConstructor
public class HttpResult<T> {

    private static final Integer SUCCESS = 200;

    private static final Integer FAIL = 500;


    private Integer code;

    private String message;

    private T resultBody;

    public HttpResult(Integer code, String message, T resultBody) {
        this.code = code;
        this.message = message;
        this.resultBody = resultBody;
    }

    public static <T> HttpResult<T> success(T resultBody) {
        return new HttpResult(SUCCESS, "success", resultBody);
    }

    public static <T> HttpResult<T> fail(T resultBody) {
        return new HttpResult<>(FAIL, "error", resultBody);
    }

    public static <T> HttpResult<T> fail(T resultBody, String message) {
        return new HttpResult<>(FAIL, message == null ? "error" : message, resultBody);
    }

}
