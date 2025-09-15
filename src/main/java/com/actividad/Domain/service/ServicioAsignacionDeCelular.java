/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.service;

import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.policy.Hechos;
import com.actividad.Domain.policy.PoliticaAsignacion;
import java.util.List;

public final class ServicioAsignacionDeCelular {

    private final List<PoliticaAsignacion> politicas;

    public ServicioAsignacionDeCelular(List<PoliticaAsignacion> politicas) {
        this.politicas = List.copyOf(politicas);
    }

    public ResultadoAsignacion puedeAsignar(Usuario u, Celular c, Hechos hechos) {
        for (PoliticaAsignacion p : politicas) {
            if (!p.cumple(u, c, hechos)) {
                return new ResultadoAsignacion(false, p.motivo());
            }
        }
        return new ResultadoAsignacion(true, null);
    }

    public record ResultadoAsignacion(boolean permitida, String motivo) {}
}

