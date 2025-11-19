/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.ListarAsignacionesUseCase;
import com.actividad.Application.port.out.AsignacionRepositoryPort;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Application.port.out.CelularRepositoryPort;
import com.actividad.Application.dto.response.AsignacionResponse;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.entity.Celular;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAsignacionesService implements ListarAsignacionesUseCase {
    private final AsignacionRepositoryPort asignacionRepo;
    private final UsuarioRepositoryPort usuarioRepo;
    private final CelularRepositoryPort celularRepo;

    public ListarAsignacionesService(AsignacionRepositoryPort asignacionRepo,
                                   UsuarioRepositoryPort usuarioRepo,
                                   CelularRepositoryPort celularRepo) {
        this.asignacionRepo = asignacionRepo;
        this.usuarioRepo = usuarioRepo;
        this.celularRepo = celularRepo;
    }

    @Override
    public List<AsignacionResponse> findAll() {
        List<AsignacionDeCelular> asignaciones = asignacionRepo.findAll();
        
        return asignaciones.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    private AsignacionResponse toResponse(AsignacionDeCelular asignacion) {
        String usuarioNombre = obtenerNombreUsuario(asignacion.usuarioId());
        String celularInfo = obtenerInfoCelular(asignacion.celularId());
        
        return new AsignacionResponse(
            asignacion.id(),
            usuarioNombre,
            celularInfo,
            asignacion.estado().name()
        );
    }

    private String obtenerNombreUsuario(String usuarioId) {
        try {
            Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
            return usuario != null ? usuario.nombre() : "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al cargar usuario";
        }
    }

    private String obtenerInfoCelular(String celularId) {
        try {
            Celular celular = celularRepo.findById(celularId).orElse(null);
            return celular != null ? 
                celular.marca() + " - " + celular.imei().value() : 
                "Celular no encontrado";
        } catch (Exception e) {
            return "Error al cargar celular";
        }
    }
}