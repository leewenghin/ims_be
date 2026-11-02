package com.example.ims.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PingResponse {
    private String message;
    private String timestamp;
    private String status;
    private String error;
    private String path;
    private String method;
    private String ip;
    private String userAgent;
    private String referer;
    private String contentType;
    private String accept;
}
