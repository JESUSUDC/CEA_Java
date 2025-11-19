package com.actividad.Application.mapper;

import com.actividad.Application.dto.response.AsignacionResponse;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Application.port.out.CelularRepositoryPort;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.entity.Celular;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AsignacionMapper {
    private final UsuarioRepositoryPort usuarioRepo;
    private final CelularRepositoryPort celularRepo;

    public AsignacionMapper(UsuarioRepositoryPort usuarioRepo, 
                          CelularRepositoryPort celularRepo) {
        this.usuarioRepo = usuarioRepo;
        this.celularRepo = celularRepo;
    }

    public AsignacionResponse toResponse(AsignacionDeCelular ra) {
        String usuarioNombre = obtenerNombreUsuario(ra.usuarioId());
        String celularInfo = obtenerInfoCelular(ra.celularId());
        
        return new AsignacionResponse(
            ra.id(), 
            ra.usuarioId(),
            usuarioNombre,
            ra.celularId(),
            celularInfo,
            ra.estado().name()
        );
    }

    private String obtenerNombreUsuario(String usuarioId) {
        try {
            Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);
            return usuario.map(Usuario::nombre)
                         .orElse("Usuario no encontrado");
        } catch (Exception e) {
            return "Error al cargar usuario";
        }
    }

    private String obtenerInfoCelular(String celularId) {
        try {
            Optional<Celular> celular = celularRepo.findById(celularId);
            return celular.map(c -> c.marca() + " - " + c.imei().value())
                         .orElse("Celular no encontrado");
        } catch (Exception e) {
            return "Error al cargar celular";
        }
    }
}