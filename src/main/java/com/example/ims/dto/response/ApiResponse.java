package com.example.ims.dto.response;

import java.time.OffsetDateTime;

import com.example.ims.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Builder.Default
    private OffsetDateTime current_date_time = DateTimeUtils.now();

    private Integer code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .message(message != null ? message : "Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> create(String message, T data) {
        return ApiResponse.<T>builder()
                .code(201)
                .message(message != null ? message : "Created")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(Integer code, String message, T data) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
