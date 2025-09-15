/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.rest;

import com.actividad.Application.port.in.AsignarCelularUseCase;
import com.actividad.Application.port.in.DevolverCelularUseCase;
import com.actividad.Infrastructure.adapter.in.rest.request.AsignarCelularRequest;
import com.actividad.Infrastructure.adapter.in.rest.response.AsignacionHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionesController {

    private final AsignarCelularUseCase asignarUC;
    private final DevolverCelularUseCase devolverUC;

    public AsignacionesController(AsignarCelularUseCase asignarUC,
                                  DevolverCelularUseCase devolverUC) {
        this.asignarUC = asignarUC;
        this.devolverUC = devolverUC;
    }

    @PostMapping
    public ResponseEntity<AsignacionHttpResponse> asignar(@RequestBody AsignarCelularRequest req) {
        String id = asignarUC.asignar(req.usuarioId(), req.celularId());
        return ResponseEntity.status(201).body(new AsignacionHttpResponse(id, "ASIGNADO"));
    }

    @PostMapping("/{asignacionId}/devolver")
    public ResponseEntity<Void> devolver(@PathVariable String asignacionId) {
        devolverUC.devolver(asignacionId);
        return ResponseEntity.noContent().build();
    }
}