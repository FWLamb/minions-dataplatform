package com.minions.util;

public class Result<T> {
    public Integer code;
    public String message;
    public T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> of(Integer code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> of(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> success(T data) {
        return Result.of(200, "success", data);
    }

    public static <T> Result<T> fail() {
        return Result.of(400, "fail");
    }

    public static <T> Result<T> fail(T data) {
        return Result.of(400, "fail", data);
    }

    public static <T> Result<T> success() {
        return Result.of(200, "success");
    }
}
