package com.actividad.Infrastructure.adapter.in.rest.response;

public record AsignacionHttpResponse(
    String asignacionId,
    String usuarioId,
    String usuarioNombre,
    String celularId,
    String celularInfo,
    String estado
) {}