/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.adapter;

import application.port.out.AsignacionRepositoryPort;
import domain.aggregate.AsignacionDeCelular;
import infrastructure.adapter.out.db.jpa.entity.AsignacionJpa;
import infrastructure.adapter.out.db.jpa.mapper.JpaDomainMapper;
import infrastructure.adapter.out.db.jpa.repo.SpringAsignacionJpaRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AsignacionRepositoryAdapter implements AsignacionRepositoryPort {
    private final SpringAsignacionJpaRepo repo;

    public AsignacionRepositoryAdapter(SpringAsignacionJpaRepo repo){ this.repo = repo; }

    @Override public void save(AsignacionDeCelular asignacion) {
        repo.save(JpaDomainMapper.toEntity(asignacion));
    }

    @Override public int activosPorUsuario(String usuarioId) {
        return repo.countActivosPorUsuario(usuarioId);
    }

    @Override public Optional<AsignacionDeCelular> findById(String asignacionId) {
        return repo.findById(asignacionId).map(JpaDomainMapper::toDomain);
    }
}