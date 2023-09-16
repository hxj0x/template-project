package hxj.boot.biz.system.framework;

import lombok.*;

import java.io.Serializable;

/**
 * @author huangxj
 * @since 2023/9/16
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CommonResult<T> {
    private Integer code = 200;
    private String msg = "";
    private T data = null;

    public static <T> CommonResult<T> success(T data) {
        return success(200, "", data);
    }

    public static <T> CommonResult<T> success(Integer code, String msg, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> CommonResult<T> error(Integer code) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = "";
        return result;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.code = 500;
        result.msg = msg;
        return result;
    }
}
