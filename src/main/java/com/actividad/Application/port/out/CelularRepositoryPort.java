/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import domain.entity.Celular;
import java.util.Optional;

public interface CelularRepositoryPort {
    Optional<Celular> findById(String celularId);
    boolean existeImeiActivo(String imei);
}
