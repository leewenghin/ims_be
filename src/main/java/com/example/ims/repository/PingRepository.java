package com.example.ims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ims.entity.Ping;

public interface PingRepository extends JpaRepository<Ping, Long> {
    Page<Ping> findAll(Pageable pageable);
}
