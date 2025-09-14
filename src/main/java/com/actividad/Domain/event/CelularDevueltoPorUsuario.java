/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.event;

import java.time.Instant;

public final class CelularDevueltoPorUsuario implements DomainEvent {
    private final String asignacionId;
    private final String usuarioId;
    private final String celularId;
    private final Instant occurredOn;

    public CelularDevueltoPorUsuario(String asignacionId, String usuarioId, String celularId) {
        this.asignacionId = asignacionId;
        this.usuarioId = usuarioId;
        this.celularId = celularId;
        this.occurredOn = Instant.now();
    }

    @Override public String name() { return "celular.devuelto.usuario"; }
    @Override public Instant occurredOn() { return occurredOn; }
}
