/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.AsignarCelularUseCase;
import com.actividad.Application.port.out.*;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.exception.DomainException;
import com.actividad.Domain.policy.Hechos;
import com.actividad.Domain.service.ServicioAsignacionDeCelular;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class AsignarCelularService implements AsignarCelularUseCase {
    private final UsuarioRepositoryPort usuarios;
    private final CelularRepositoryPort celulares;
    private final AsignacionRepositoryPort asignaciones;
    private final DomainEventPublisherPort publisher;
    private final ServicioAsignacionDeCelular validador;

    public AsignarCelularService(UsuarioRepositoryPort usuarios,
                                 CelularRepositoryPort celulares,
                                 AsignacionRepositoryPort asignaciones,
                                 DomainEventPublisherPort publisher,
                                 ServicioAsignacionDeCelular validador) {
        this.usuarios = usuarios;
        this.celulares = celulares;
        this.asignaciones = asignaciones;
        this.publisher = publisher;
        this.validador = validador;
    }

    @Override
    public String asignar(String usuarioId, String celularId) {
        Usuario u = usuarios.findById(usuarioId)
            .orElseThrow(() -> new DomainException("Usuario no existe"));
        Celular c = celulares.findById(celularId)
            .orElseThrow(() -> new DomainException("Celular no existe"));

        var hechos = new Hechos(
            celulares.existeImeiActivo(c.imei().value()),
            asignaciones.activosPorUsuario(u.id()),
            2 // ejemplo: límite configurable
        );

        var res = validador.puedeAsignar(u, c, hechos);
        if (!res.permitida()) throw new DomainException(res.motivo());

        var ra = new AsignacionDeCelular(UUID.randomUUID().toString());
        ra.asignar(u.id(), c.id(),
                   hechos.imeiDuplicado(), hechos.activosUsuario(), hechos.maxActivos(),
                   c.tieneNfc(), c.tieneHuella());

        asignaciones.save(ra);
        publisher.publish(ra.pullEvents());
        return ra.id();
    }
}
