/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.entity;

import jakarta.persistence.*;

@Entity @Table(name = "asignaciones")
public class AsignacionJpa {
    @Id
    private String asignacionId;
    private String usuarioId;
    private String celularId;
    private String estado; // ASIGNADO|DEVUELTO

    // getters & setters
    public String getAsignacionId() { return asignacionId; }
    public void setAsignacionId(String asignacionId) { this.asignacionId = asignacionId; }
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public String getCelularId() { return celularId; }
    public void setCelularId(String celularId) { this.celularId = celularId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
