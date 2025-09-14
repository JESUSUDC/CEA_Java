/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import application.port.in.DevolverCelularUseCase;
import application.port.out.AsignacionRepositoryPort;
import application.port.out.DomainEventPublisherPort;
import domain.aggregate.AsignacionDeCelular;
import domain.exception.DomainException;

public final class DevolverCelularService implements DevolverCelularUseCase {
    private final AsignacionRepositoryPort asignaciones;
    private final DomainEventPublisherPort publisher;

    public DevolverCelularService(AsignacionRepositoryPort asignaciones,
                                  DomainEventPublisherPort publisher) {
        this.asignaciones = asignaciones;
        this.publisher = publisher;
    }

    @Override
    public void devolver(String asignacionId) {
        AsignacionDeCelular ra = asignaciones.findById(asignacionId)
            .orElseThrow(() -> new DomainException("Asignación no existe"));

        ra.devolver();
        asignaciones.save(ra);
        publisher.publish(ra.pullEvents());
    }
}
