
package com.actividad.Domain.valueObject;

import com.actividad.Domain.exception.DomainException;
import java.util.Objects;

public final class Imei {
    private final String value;

    private Imei(String raw) {
        String v = raw == null ? "" : raw.trim();
        if (!v.matches("\\d{14,15}") || !passesLuhn(v)) {
            throw DomainException.business("IMEI inválido: " + raw);
        }
        this.value = v;
    }

    public static Imei of(String raw) { return new Imei(raw); }
    public String value() { return value; }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Imei)) return false;
        return value.equals(((Imei)o).value);
    }
    @Override public int hashCode(){ return Objects.hash(value); }
    @Override public String toString(){ return value; }

    private static boolean passesLuhn(String s){
        int sum = 0; boolean alt = false;
        for (int i = s.length()-1; i >= 0; i--) {
            int n = s.charAt(i) - '0';
            if (alt) { n *= 2; if (n > 9) n -= 9; }
            sum += n; alt = !alt;
        }
        return sum % 10 == 0;
    }
}
