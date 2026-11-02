package com.example.ims.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ims.dto.request.PingRequest;
import com.example.ims.dto.response.PingResponse;
import com.example.ims.entity.Ping;
import com.example.ims.repository.PingRepository;
import com.example.ims.utils.DateTimeUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PingService {
    private final PingRepository pingRepository;

    @Transactional(readOnly = true)
    public Page<PingResponse> getPage(Pageable pageable) {
        log.info("Getting pageable: {}", pageable);
        return pingRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Transactional
    public PingResponse create(PingRequest request) {
        Ping saved = pingRepository.save(Ping.builder()
                .id(request.getId())
                .build());
        log.info("Saved ping row id={}", saved.getId());
        return mapToResponse(saved);
    }

    private PingResponse mapToResponse(Ping entity) {
        return PingResponse.builder()
                .message("Success")
                .timestamp(DateTimeUtils.now().toString())
                .status("OK")
                .error(null)
                .path(null)
                .method(null)
                .ip(null)
                .userAgent(null)
                .referer(null)
                .contentType(null)
                .accept(null)
                .build();
    }
}
