/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.rest;

import com.actividad.Application.port.in.UsuarioCrudUseCase;
import com.actividad.Application.dto.command.CreateUsuarioCommand;
import com.actividad.Application.dto.command.UpdateUsuarioCommand;
import com.actividad.Domain.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioCrudUseCase userCase;

    public UsuarioController(UsuarioCrudUseCase userCase) {
        this.userCase = userCase;
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody CreateUsuarioCommand cmd) {
        Usuario usuario = userCase.create(cmd);
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> update(@PathVariable String usuarioId, @RequestBody UpdateUsuarioCommand cmd) {
        userCase.update(new UpdateUsuarioCommand(usuarioId, cmd.nombre(), cmd.email()));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> delete(@PathVariable String usuarioId) {
        userCase.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getById(@PathVariable String usuarioId) {
        return ResponseEntity.ok(userCase.findById(usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(userCase.findAll());
    }
}
