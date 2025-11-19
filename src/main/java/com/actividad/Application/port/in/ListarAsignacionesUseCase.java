/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.in;

import com.actividad.Application.dto.response.AsignacionResponse;

import java.util.List;

public interface ListarAsignacionesUseCase {
    List<AsignacionResponse> findAll();
}
