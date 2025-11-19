/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.in;

import com.actividad.Domain.entity.Celular;
import com.actividad.Application.dto.command.CreateCelularCommand;
import com.actividad.Application.dto.command.UpdateCelularCommand;
import java.util.List;

public interface CelularCrudUseCase {
    Celular create(CreateCelularCommand cmd);
    void update(UpdateCelularCommand cmd);
    void delete(String celularId);
    Celular findById(String celularId);
    List<Celular> findAll();
}
