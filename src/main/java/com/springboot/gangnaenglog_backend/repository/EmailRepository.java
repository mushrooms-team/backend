package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
