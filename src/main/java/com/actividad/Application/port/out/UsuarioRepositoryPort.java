/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import com.actividad.Domain.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findById(String usuarioId);
}
