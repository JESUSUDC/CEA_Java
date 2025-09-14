/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.adapter;

import application.port.out.CelularRepositoryPort;
import domain.entity.Celular;
import infrastructure.adapter.out.db.jpa.mapper.JpaDomainMapper;
import infrastructure.adapter.out.db.jpa.repo.SpringCelularJpaRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CelularRepositoryAdapter implements CelularRepositoryPort {
    private final SpringCelularJpaRepo repo;
    public CelularRepositoryAdapter(SpringCelularJpaRepo repo){ this.repo = repo; }

    @Override public Optional<Celular> findById(String celularId) {
        return repo.findById(celularId).map(JpaDomainMapper::toDomain);
    }

    @Override public boolean existeImeiActivo(String imei) {
        return repo.existsImeiAsignado(imei);
    }
}