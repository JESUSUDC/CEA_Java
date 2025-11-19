package com.actividad.Domain.valueObject;

import com.actividad.Domain.exception.DomainException;
import java.util.Objects;

public final class Password {
    private final String value;

    private Password(String value) {
        if (value == null || value.isBlank()) throw DomainException.required("Password");
        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }

    public String value() {
        return value;
    }

    // Para comparar hashes (no plano! solo hashes)
    public boolean matches(String hash) {
        return Objects.equals(value, hash);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Password p) && Objects.equals(value, p.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
