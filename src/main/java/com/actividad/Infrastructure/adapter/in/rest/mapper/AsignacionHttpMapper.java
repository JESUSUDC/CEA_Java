package com.actividad.Infrastructure.adapter.in.rest.mapper;

import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Infrastructure.adapter.in.rest.response.AsignacionHttpResponse;
import org.springframework.stereotype.Component;

/**
 * ACL (HTTP) – Traduce objetos del dominio a respuestas HTTP.
 */
@Component
public class AsignacionHttpMapper {

    /** Respuesta estándar al crear una asignación. */
    public AsignacionHttpResponse created(String asignacionId, String usuarioId, String celularId) {
        return new AsignacionHttpResponse(
            asignacionId, 
            usuarioId,
            "Usuario " + usuarioId, // Podrías obtener el nombre real si lo necesitas
            celularId,
            "Celular " + celularId, // Podrías obtener la info real si lo necesitas
            "ASIGNADO"
        );
    }

    /** Mapea directamente desde la RA (útil para GET/POST). */
    public AsignacionHttpResponse fromAggregate(AsignacionDeCelular ra) {
        return new AsignacionHttpResponse(
            ra.id(),
            ra.usuarioId(),
            "Usuario " + ra.usuarioId(), // Necesitarías inyectar un servicio para obtener el nombre real
            ra.celularId(),
            "Celular " + ra.celularId(), // Necesitarías inyectar un servicio para obtener la info real
            ra.estado().name()
        );
    }

    /** Respuesta para la operación de devolución. */
    public AsignacionHttpResponse devolucion(String asignacionId) {
        return new AsignacionHttpResponse(
            asignacionId,
            null, // No disponible en este contexto
            null,
            null,
            null,
            "DEVUELTO"
        );
    }
}