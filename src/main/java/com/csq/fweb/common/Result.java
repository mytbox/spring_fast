package com.csq.fweb.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "generic return result class")
public class Result<T> {
    @Schema(description = "status code", example = "200")
    private int code;
    @Schema(description = "Message", example = "The operation was successful")
    private String message;
    @Schema(description = "Data")
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "The operation was successful", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, "The operation was successful", null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}