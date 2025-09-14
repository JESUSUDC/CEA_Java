/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.valueObject;

import domain.exception.DomainException;
import java.util.Objects;

public final class Email {
    private final String value;

    private Email(String raw){
        String v = raw == null ? "" : raw.trim().toLowerCase();
        if (!v.matches(".+@.+\\..+")) {
            throw DomainException.business("Email inválido: " + raw);
        }
        this.value = v;
    }

    public static Email of(String raw){ return new Email(raw); }
    public String value(){ return value; }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        return value.equals(((Email)o).value);
    }
    @Override public int hashCode(){ return Objects.hash(value); }
    @Override public String toString(){ return value; }
}