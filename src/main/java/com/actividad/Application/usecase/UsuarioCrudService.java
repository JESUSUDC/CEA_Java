/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.UsuarioCrudUseCase;
import com.actividad.Application.dto.command.CreateUsuarioCommand;
import com.actividad.Application.dto.command.UpdateUsuarioCommand;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Email;
import com.actividad.Domain.valueObject.Password;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class UsuarioCrudService implements UsuarioCrudUseCase {
    private final UsuarioRepositoryPort repo;
    //private final PasswordEncoder encoder;

    public UsuarioCrudService(UsuarioRepositoryPort repo/*, PasswordEncoder encoder*/) {
        this.repo = repo;
        //this.encoder = encoder;
    }

    @Override
    public Usuario create(CreateUsuarioCommand cmd) {
        Usuario u = Usuario.create(
                UUID.randomUUID().toString(),
                cmd.nombre(),
                Email.of(cmd.email()),
                //Password.of(encoder.encode(cmd.password()))
                Password.of(cmd.password())
        );
        repo.save(u);
        return u;
    }

    @Override
    public void update(UpdateUsuarioCommand cmd) {
        Usuario u = repo.findById(cmd.usuarioId()).orElseThrow();
        u.rename(cmd.nombre());
        u.changeEmail(Email.of(cmd.email()));
        repo.save(u);
    }

    @Override
    public void delete(String usuarioId) {
        repo.delete(usuarioId);
    }

    @Override
    public Usuario findById(String usuarioId) {
        return repo.findById(usuarioId).orElseThrow();
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }
    
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public void updatePassword(Usuario usuario) {
        repo.save(usuario); // Si ya existe, solo reusa el .save del repo
    }
}