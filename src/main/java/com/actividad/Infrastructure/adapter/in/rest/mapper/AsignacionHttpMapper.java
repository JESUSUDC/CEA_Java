/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.rest.mapper;

import domain.aggregate.AsignacionDeCelular;
import infrastructure.adapter.in.rest.response.AsignacionHttpResponse;
import org.springframework.stereotype.Component;

/**
 * ACL (HTTP) – Traduce objetos del dominio a respuestas HTTP.
 */
@Component
public class AsignacionHttpMapper {

    /** Respuesta estándar al crear una asignación. */
    public AsignacionHttpResponse created(String asignacionId) {
        return new AsignacionHttpResponse(asignacionId, "ASIGNADO");
    }

    /** Mapea directamente desde la RA (útil para GET/POST). */
    public AsignacionHttpResponse fromAggregate(AsignacionDeCelular ra) {
        return new AsignacionHttpResponse(ra.id(), ra.estado().name());
    }

    /** Respuesta para la operación de devolución. */
    public AsignacionHttpResponse devolucion(String asignacionId) {
        return new AsignacionHttpResponse(asignacionId, "DEVUELTO");
    }
}
