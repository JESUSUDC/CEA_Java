/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.rest;

import com.actividad.Application.port.in.CelularCrudUseCase;
import com.actividad.Application.dto.command.CreateCelularCommand;
import com.actividad.Application.dto.command.UpdateCelularCommand;
import com.actividad.Domain.entity.Celular;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celulares")
public class CelularController {
    private final CelularCrudUseCase useCase;

    public CelularController(CelularCrudUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Celular> create(@RequestBody CreateCelularCommand cmd) {
        return ResponseEntity.status(201).body(useCase.create(cmd));
    }

    @PutMapping("/{celularId}")
    public ResponseEntity<Void> update(@PathVariable String celularId, @RequestBody UpdateCelularCommand cmd) {
        useCase.update(new UpdateCelularCommand(
            celularId,
            cmd.marca(),
            cmd.operador(),
            cmd.tecnologiaBanda(),
            cmd.cantidadSim()
        ));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{celularId}")
    public ResponseEntity<Void> delete(@PathVariable String celularId) {
        useCase.delete(celularId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{celularId}")
    public ResponseEntity<Celular> getById(@PathVariable String celularId) {
        return ResponseEntity.ok(useCase.findById(celularId));
    }

    @GetMapping
    public ResponseEntity<List<Celular>> findAll() {
        return ResponseEntity.ok(useCase.findAll());
    }
}
