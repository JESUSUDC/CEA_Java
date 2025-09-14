/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.policy;

import domain.entity.Celular;
import domain.entity.Usuario;

public final class PoliticaCapacidadesMinimas implements PoliticaAsignacion {
    private String motivo = "";

    @Override public boolean cumple(Usuario u, Celular c, Hechos h) {
        if (!c.tieneNfc() || !c.tieneHuella()) {
            motivo = "El celular no cumple NFC y huella";
            return false;
        }
        return true;
    }

    @Override public String motivo() { return motivo; }
}