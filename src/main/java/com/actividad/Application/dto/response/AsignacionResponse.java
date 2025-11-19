package com.actividad.Application.dto.response;

public record AsignacionResponse(
    String id,
    String usuarioId,
    String usuarioNombre,
    String celularId, 
    String celularInfo,
    String estado
) {}