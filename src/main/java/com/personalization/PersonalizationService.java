package com.personalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories(basePackages = "com.personalization.repository")
@EntityScan(basePackages = "com.personalization.entity")
@SpringBootApplication
@EnableAsync
public class PersonalizationService {
    public static void main(String[] args) {
        SpringApplication.run(PersonalizationService.class, args);

    }
}