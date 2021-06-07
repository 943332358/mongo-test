package org.yx.mongotest.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangxin
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    public int code;

    public T data;

    public String message;


    public Result() {
    }

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static Result<String> ok() {
        return new Result<>(0, "success", "操作成功");
    }

    public static <T> Result<T> ok(T t) {
        return new Result<>(0, t, "操作成功");
    }

    public static <T> Result<T> failed(T t) {
        return new Result<>(1, t, "操作失败");
    }
}
