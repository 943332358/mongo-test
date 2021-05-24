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
}
