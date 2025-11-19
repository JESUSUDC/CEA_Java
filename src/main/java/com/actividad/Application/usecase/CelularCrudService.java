/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.CelularCrudUseCase;
import com.actividad.Application.dto.command.CreateCelularCommand;
import com.actividad.Application.dto.command.UpdateCelularCommand;
import com.actividad.Application.port.out.CelularRepositoryPort;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.valueObject.Imei;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CelularCrudService implements CelularCrudUseCase {
    private final CelularRepositoryPort repo;

    public CelularCrudService(CelularRepositoryPort repo) {
        this.repo = repo;
    }

    @Override
    public Celular create(CreateCelularCommand cmd) {
        Celular c = Celular.registrar(
            UUID.randomUUID().toString(),
            cmd.marca(),
            Imei.of(cmd.imei()),
            cmd.nfc(),
            cmd.huella(),
            cmd.operador(),
            cmd.tecnologiaBanda(),
            cmd.cantidadSim()
        );
        repo.save(c);
        return c;
    }

    @Override
    public void update(UpdateCelularCommand cmd) {
        Celular c = repo.findById(cmd.celularId()).orElseThrow();
        c.actualizarOperador(cmd.operador(), cmd.tecnologiaBanda());
        // Opcional: si deseas, agrega métodos en Celular para actualizar marca/cantidadSim
        repo.save(c);
    }

    @Override
    public void delete(String celularId) {
        repo.delete(celularId);
    }

    @Override
    public Celular findById(String celularId) {
        return repo.findById(celularId).orElseThrow();
    }

    @Override
    public List<Celular> findAll() {
        return repo.findAll();
    }
}
