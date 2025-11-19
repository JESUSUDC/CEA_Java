/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.event;

import java.util.List;
import java.util.Optional;
import java.time.Instant;


public final class UsuarioSolicitudCambioPassword implements DomainEvent {
    private final String usuarioId;
    private final Instant occurredOn;
    // Opcional: token, motivo, etc.

    public UsuarioSolicitudCambioPassword(String usuarioId/*, extras*/) {
        this.usuarioId = usuarioId;
        this.occurredOn = Instant.now();
    }
    @Override public String name() { return "usuario.solicitud_cambio_password"; }
    @Override public Instant occurredOn() { return occurredOn; }
    public String usuarioId() { return usuarioId; }
}
