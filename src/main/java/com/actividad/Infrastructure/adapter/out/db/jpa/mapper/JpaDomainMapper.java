/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.mapper;

import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Imei;
import com.actividad.Domain.valueObject.Email;
import com.actividad.Infrastructure.adapter.out.db.jpa.entity.*;

public final class JpaDomainMapper {
    private JpaDomainMapper(){}

    public static Usuario toDomain(UsuarioJpa e) {
        return Usuario.create(e.getUsuarioId(), e.getNombre(), Email.of(e.getEmail()));
    }

    public static Celular toDomain(CelularJpa e) {
        return Celular.registrar(e.getCelularId(), e.getMarca(), Imei.of(e.getImei()),
                e.isNfc(), e.isHuella(), e.getOperador(), e.getTecnologiaBanda(), e.getCantidadSim());
    }

    public static AsignacionJpa toEntity(AsignacionDeCelular ra) {
        AsignacionJpa e = new AsignacionJpa();
        e.setAsignacionId(ra.id());
        e.setUsuarioId(ra.usuarioId());
        e.setCelularId(ra.celularId());
        e.setEstado(ra.estado().name());
        return e;
    }

    // Si necesitas rehidratar RA desde JPA (para devolver), añade inverse mapping:
    public static AsignacionDeCelular toDomain(AsignacionJpa e) {
        var ra = new AsignacionDeCelular(e.getAsignacionId());
        // estado/ids se setean mediante comportamiento; para simplificar podrías:
        // - extender RA con un método de rehidratación controlada (factory reconstructora)
        // o
        // - mantener lectura de RA como proyección y usar métodos de dominio para transiciones.
        // Para ejemplo simple, omitimos reconstrucción profunda.
        return ra;
    }
}