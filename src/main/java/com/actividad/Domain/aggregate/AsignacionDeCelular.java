/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.aggregate;

import domain.event.CelularAsignadoAUsuario;
import domain.event.CelularDevueltoPorUsuario;
import domain.event.DomainEvent;
import domain.exception.DomainException;
import domain.exception.ImeiDuplicadoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AsignacionDeCelular {
    public enum Estado { ASIGNADO, DEVUELTO }

    private final String asignacionId;
    private String usuarioId;
    private String celularId;
    private Estado estado = Estado.DEVUELTO;

    private final List<DomainEvent> events = new ArrayList<>();

    public AsignacionDeCelular(String asignacionId) {
        if (asignacionId == null || asignacionId.isBlank()) throw DomainException.required("AsignacionId");
        this.asignacionId = asignacionId;
    }

    /**
     * Invariantes:
     * - IMEI único activo (precalculado en aplicación → imeiDuplicado=false)
     * - Límite por usuario (activosUsuario < maxActivos)
     * - Capacidades mínimas (nfcOk && huellaOk)
     */
    public void asignar(String usuarioId, String celularId,
                        boolean imeiDuplicado,
                        int activosUsuario, int maxActivos,
                        boolean nfcOk, boolean huellaOk) {
        if (imeiDuplicado) throw new ImeiDuplicadoException(celularId);
        if (activosUsuario >= maxActivos) throw DomainException.business("Límite por usuario excedido");
        if (!nfcOk || !huellaOk) throw DomainException.business("Equipo no cumple NFC/huella");

        this.usuarioId = notBlank(usuarioId, "UsuarioId");
        this.celularId = notBlank(celularId, "CelularId");
        this.estado = Estado.ASIGNADO;
        record(new CelularAsignadoAUsuario(asignacionId, usuarioId, celularId));
    }

    public void devolver() {
        if (estado != Estado.ASIGNADO) throw DomainException.business("No puede devolverse si no está ASIGNADO");
        this.estado = Estado.DEVUELTO;
        record(new CelularDevueltoPorUsuario(asignacionId, usuarioId, celularId));
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> out = List.copyOf(events);
        events.clear();
        return out;
    }

    private void record(DomainEvent e){ events.add(e); }
    private static String notBlank(String v, String f){
        if (v == null || v.isBlank()) throw DomainException.required(f);
        return v.trim();
    }

    public String id(){ return asignacionId; }
    public Estado estado(){ return estado; }
    public String usuarioId(){ return usuarioId; }
    public String celularId(){ return celularId; }
}
