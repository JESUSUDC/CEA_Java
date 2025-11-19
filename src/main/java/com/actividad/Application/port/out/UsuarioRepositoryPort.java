/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import com.actividad.Domain.entity.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findById(String usuarioId);
    Optional<Usuario> findByEmail(String email); // Para login y única por email
    void save(Usuario usuario); // Para create y update
    void delete(String usuarioId);
    List<Usuario> findAll();
}
