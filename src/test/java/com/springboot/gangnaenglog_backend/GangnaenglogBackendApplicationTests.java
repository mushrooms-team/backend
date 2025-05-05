package com.springboot.gangnaenglog_backend;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GangnaenglogBackendApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(GangnaenglogBackendApplicationTests.class, args);
    }
}