package com.csq.fweb.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "通用返回结果类")
public class Result<T> {
    @Schema(description = "状态码", example = "200")
    private int code;
    @Schema(description = "消息", example = "操作成功")
    private String message;
    @Schema(description = "数据")
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}