/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.event;

import java.time.Instant;

public final class CelularAsignadoAUsuario implements DomainEvent {
    private final String asignacionId;
    private final String usuarioId;
    private final String celularId;
    private final Instant occurredOn;

    public CelularAsignadoAUsuario(String asignacionId, String usuarioId, String celularId) {
        this.asignacionId = asignacionId;
        this.usuarioId = usuarioId;
        this.celularId = celularId;
        this.occurredOn = Instant.now();
    }

    @Override public String name() { return "celular.asignado.usuario"; }
    @Override public Instant occurredOn() { return occurredOn; }

    public String asignacionId(){ return asignacionId; }
    public String usuarioId(){ return usuarioId; }
    public String celularId(){ return celularId; }
}