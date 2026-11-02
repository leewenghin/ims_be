package com.example.ims.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ims.dto.request.PaginationRequest;
import com.example.ims.dto.request.PingRequest;
import com.example.ims.dto.response.ApiResponse;
import com.example.ims.dto.response.PageResponse;
import com.example.ims.dto.response.PingResponse;
import com.example.ims.entity.Ping;
import com.example.ims.service.PingService;
import com.example.ims.utils.PageUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({ "/api/v1/ping" })
@RequiredArgsConstructor
public class PingController {
    private final PingService pingService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PingResponse>>> getTest(@Valid PaginationRequest pagination) {
        PageResponse<PingResponse> page = PageResponse
                .from(pingService.getPage(PageUtils.adjustPageable(pagination, Ping.class)));

        return ResponseEntity.ok(ApiResponse.success("Success", page));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PingResponse>> createTest(@Valid @ModelAttribute PingRequest request) {
        PingResponse created = pingService.create(request);
        return ResponseEntity.ok(ApiResponse.create("Created", created));
    }
}
