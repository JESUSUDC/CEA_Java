/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import application.port.in.AsignarCelularUseCase;
import application.port.out.*;
import domain.aggregate.AsignacionDeCelular;
import domain.entity.Celular;
import domain.entity.Usuario;
import domain.exception.DomainException;
import domain.policy.Hechos;
import domain.service.ServicioAsignacionDeCelular;

import java.util.UUID;

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
