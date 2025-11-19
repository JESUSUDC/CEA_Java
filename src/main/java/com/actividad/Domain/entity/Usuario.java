package com.actividad.Domain.entity;

import com.actividad.Domain.exception.DomainException;
import com.actividad.Domain.valueObject.Email;
import com.actividad.Domain.valueObject.Password; // recuerda definir este VO
import java.util.Objects;

public final class Usuario {
    private final String usuarioId;     // Identidad estable
    private String nombre;
    private Email email;
    private Password password;

    private Usuario(String usuarioId, String nombre, Email email, Password password) {
        if (usuarioId == null || usuarioId.isBlank()) throw DomainException.required("UsuarioId");
        rename(nombre);
        changeEmail(email);
        changePassword(password);
        this.usuarioId = usuarioId;
    }

    public static Usuario create(String usuarioId, String nombre, Email email, Password password) {
        return new Usuario(usuarioId, nombre, email, password);
    }

    public String id() { return usuarioId; }
    public String nombre() { return nombre; }
    public Email email() { return email; }
    public Password password() { return password; }

    public void rename(String nuevo) {
        if (nuevo == null || nuevo.isBlank()) throw DomainException.required("Nombre");
        this.nombre = nuevo.trim();
    }

    public void changeEmail(Email nuevo) {
        if (nuevo == null) throw DomainException.required("Email");
        this.email = nuevo;
    }

    public void changePassword(Password nuevo) {
        if (nuevo == null) throw DomainException.required("Password");
        this.password = nuevo;
    }

    // Para verificar autenticación: compara hashes (acá solo compara value)
    public boolean passwordMatches(String rawHash) {
        return password.value().equals(rawHash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return usuarioId.equals(((Usuario) o).usuarioId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(usuarioId);
    }
}
