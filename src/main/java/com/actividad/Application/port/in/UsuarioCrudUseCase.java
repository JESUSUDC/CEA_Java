/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.in;

import com.actividad.Domain.entity.Usuario;
import com.actividad.Application.dto.command.CreateUsuarioCommand;
import com.actividad.Application.dto.command.UpdateUsuarioCommand;
import java.util.List;
import java.util.Optional;

public interface UsuarioCrudUseCase {
    Usuario create(CreateUsuarioCommand cmd);
    void update(UpdateUsuarioCommand cmd);
    void delete(String usuarioId);
    Usuario findById(String usuarioId);
    List<Usuario> findAll();
    Optional<Usuario> findByEmail(String email);
    void updatePassword(Usuario usuario);
}
