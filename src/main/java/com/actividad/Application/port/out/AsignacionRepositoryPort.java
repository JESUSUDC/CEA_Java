/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import domain.aggregate.AsignacionDeCelular;
import java.util.Optional;

public interface AsignacionRepositoryPort {
    void save(AsignacionDeCelular asignacion);
    int activosPorUsuario(String usuarioId);
    Optional<AsignacionDeCelular> findById(String asignacionId); // para devolver()
}
