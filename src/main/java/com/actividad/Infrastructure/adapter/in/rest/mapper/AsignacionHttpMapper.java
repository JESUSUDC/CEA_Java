package com.actividad.Infrastructure.adapter.in.rest.mapper;

import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Infrastructure.adapter.in.rest.response.AsignacionHttpResponse;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Application.port.out.CelularRepositoryPort;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.entity.Celular;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AsignacionHttpMapper {
    private final UsuarioRepositoryPort usuarioRepo;
    private final CelularRepositoryPort celularRepo;

    public AsignacionHttpMapper(UsuarioRepositoryPort usuarioRepo, 
                              CelularRepositoryPort celularRepo) {
        this.usuarioRepo = usuarioRepo;
        this.celularRepo = celularRepo;
    }

    public AsignacionHttpResponse created(String asignacionId, String usuarioId, String celularId) {
        String usuarioNombre = obtenerNombreUsuario(usuarioId);
        String celularInfo = obtenerInfoCelular(celularId);
        
        return new AsignacionHttpResponse(
            asignacionId, 
            usuarioId,
            usuarioNombre,
            celularId,
            celularInfo,
            "ASIGNADO"
        );
    }

    public AsignacionHttpResponse fromAggregate(AsignacionDeCelular ra) {
        String usuarioNombre = obtenerNombreUsuario(ra.usuarioId());
        String celularInfo = obtenerInfoCelular(ra.celularId());
        
        return new AsignacionHttpResponse(
            ra.id(),
            ra.usuarioId(),
            usuarioNombre,
            ra.celularId(),
            celularInfo,
            ra.estado().name()
        );
    }

    public AsignacionHttpResponse devolucion(AsignacionDeCelular ra) {
        String usuarioNombre = obtenerNombreUsuario(ra.usuarioId());
        String celularInfo = obtenerInfoCelular(ra.celularId());
        
        return new AsignacionHttpResponse(
            ra.id(),
            ra.usuarioId(),
            usuarioNombre,
            ra.celularId(),
            celularInfo,
            "DEVUELTO"
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