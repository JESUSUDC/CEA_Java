/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.mapper;

import com.actividad.Application.dto.response.AsignacionResponse;
import com.actividad.Domain.aggregate.AsignacionDeCelular;

public final class AsignacionMapper {
    private AsignacionMapper(){}

    public static AsignacionResponse toResponse(AsignacionDeCelular ra) {
        return new AsignacionResponse(ra.id(), ra.estado().name());
    }
}
