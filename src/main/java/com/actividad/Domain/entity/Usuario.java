/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.entity;

import com.actividad.Domain.exception.DomainException;
import com.actividad.Domain.valueObject.Email;
import java.util.Objects;

public final class Usuario {
    private final String usuarioId;     // identidad estable
    private String nombre;
    private Email email;

    private Usuario(String usuarioId, String nombre, Email email){
        if (usuarioId == null || usuarioId.isBlank()) throw DomainException.required("UsuarioId");
        rename(nombre);
        changeEmail(email);
        this.usuarioId = usuarioId;
    }

    public static Usuario create(String usuarioId, String nombre, Email email){
        return new Usuario(usuarioId, nombre, email);
    }

    public String id(){ return usuarioId; }
    public String nombre(){ return nombre; }
    public Email email(){ return email; }

    public void rename(String nuevo){
        if (nuevo == null || nuevo.isBlank()) throw DomainException.required("Nombre");
        this.nombre = nuevo.trim();
    }

    public void changeEmail(Email nuevo){
        if (nuevo == null) throw DomainException.required("Email");
        this.email = nuevo;
    }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return usuarioId.equals(((Usuario)o).usuarioId);
    }
    @Override public int hashCode(){ return Objects.hash(usuarioId); }
}
