/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.DevolverCelularUseCase;
import com.actividad.Application.port.out.AsignacionRepositoryPort;
import com.actividad.Application.port.out.DomainEventPublisherPort;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.exception.DomainException;
import org.springframework.stereotype.Service;

@Service
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
