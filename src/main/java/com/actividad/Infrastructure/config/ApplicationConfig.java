/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.config;

import com.actividad.Application.port.in.AsignarCelularUseCase;
import com.actividad.Application.port.in.DevolverCelularUseCase;
import com.actividad.Application.port.out.*;
import com.actividad.Application.usecase.AsignarCelularService;
import com.actividad.Application.usecase.DevolverCelularService;
import com.actividad.Domain.policy.PoliticaCapacidadesMinimas;
import com.actividad.Domain.policy.PoliticaLimitePorUsuario;
import com.actividad.Domain.policy.PoliticaUnicidadImei;
import com.actividad.Domain.service.ServicioAsignacionDeCelular;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.actividad.Infrastructure.adapter.out.jwt.JwtProvider;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;


import java.util.List;

@Configuration
public class ApplicationConfig {
    
    
    @Bean
    ServicioAsignacionDeCelular servicioAsignacionDeCelular() {
        return new ServicioAsignacionDeCelular(List.of(
            new PoliticaUnicidadImei(),
            new PoliticaLimitePorUsuario(),
            new PoliticaCapacidadesMinimas()
        ));
    }
    
    /*@Bean
    @Primary  // ← MARCA ESTE BEAN COMO PRIMARIO
    public AsignarCelularUseCase asignarCelularUseCase(
        AsignarCelularService asignarCelularService) {
        return asignarCelularService;
    }

    @Bean
    AsignarCelularUseCase asignarCelularUseCase(UsuarioRepositoryPort usuarios,
                                               CelularRepositoryPort celulares,
                                               AsignacionRepositoryPort asignaciones,
                                               DomainEventPublisherPort publisher,
                                               ServicioAsignacionDeCelular validador) {
        return new AsignarCelularService(usuarios, celulares, asignaciones, publisher, validador);
    }

    @Bean
    DevolverCelularUseCase devolverCelularUseCase(AsignacionRepositoryPort asignaciones,
                                                  DomainEventPublisherPort publisher) {
        return new DevolverCelularService(asignaciones, publisher);
    }*/
    
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}