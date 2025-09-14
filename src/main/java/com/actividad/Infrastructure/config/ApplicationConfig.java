/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.config;

import application.port.in.AsignarCelularUseCase;
import application.port.in.DevolverCelularUseCase;
import application.port.out.*;
import application.usecase.AsignarCelularService;
import application.usecase.DevolverCelularService;
import domain.policy.PoliticaCapacidadesMinimas;
import domain.policy.PoliticaLimitePorUsuario;
import domain.policy.PoliticaUnicidadImei;
import domain.service.ServicioAsignacionDeCelular;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    }
}