/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import com.actividad.Domain.entity.Celular;
import java.util.Optional;
import java.util.List;

public interface CelularRepositoryPort {
    Optional<Celular> findById(String celularId);
    void save(Celular celular); // Para create y update
    void delete(String celularId);
    List<Celular> findAll();
    boolean existeImeiActivo(String imei); // (seguridad y reglas de negocio)
}
