package com.kwakmunsu.elitery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EliteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EliteryApplication.class, args);
    }

}