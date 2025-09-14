/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.adapter;

import application.port.out.UsuarioRepositoryPort;
import domain.entity.Usuario;
import infrastructure.adapter.out.db.jpa.mapper.JpaDomainMapper;
import infrastructure.adapter.out.db.jpa.repo.SpringUsuarioJpaRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    private final SpringUsuarioJpaRepo repo;
    public UsuarioRepositoryAdapter(SpringUsuarioJpaRepo repo){ this.repo = repo; }

    @Override public Optional<Usuario> findById(String usuarioId) {
        return repo.findById(usuarioId).map(JpaDomainMapper::toDomain);
    }
}