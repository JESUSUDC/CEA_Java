package com.actividad.CEA_Java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.actividad.CEA_Java",
    "com.actividad.Application", 
    "com.actividad.Domain",
    "com.actividad.Infrastructure"
})
@EntityScan(basePackages = {
    "com.actividad.Infrastructure.adapter.out.db.jpa.entity",
    "com.actividad.Domain.entity" // por si acaso
})
@EnableJpaRepositories(basePackages = {
    "com.actividad.Infrastructure.adapter.out.db.jpa.repo"
})
public class CeaJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CeaJavaApplication.class, args);
    }
}