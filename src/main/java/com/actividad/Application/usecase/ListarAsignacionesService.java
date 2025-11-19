package com.actividad.Application.usecase;

import com.actividad.Application.port.in.ListarAsignacionesUseCase;
import com.actividad.Application.port.out.AsignacionRepositoryPort;
import com.actividad.Application.dto.response.AsignacionResponse;
import com.actividad.Application.mapper.AsignacionMapper;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAsignacionesService implements ListarAsignacionesUseCase {
    private final AsignacionRepositoryPort asignacionRepo;
    private final AsignacionMapper asignacionMapper;

    public ListarAsignacionesService(AsignacionRepositoryPort asignacionRepo,
                                   AsignacionMapper asignacionMapper) {
        this.asignacionRepo = asignacionRepo;
        this.asignacionMapper = asignacionMapper;
    }

    @Override
    public List<AsignacionResponse> findAll() {
        List<AsignacionDeCelular> asignaciones = asignacionRepo.findAll();
        
        // DEBUG: Ver qué datos tenemos
        System.out.println("=== DEBUG ASIGNACIONES ===");
        for (AsignacionDeCelular asignacion : asignaciones) {
            System.out.println("Asignacion ID: " + asignacion.id());
            System.out.println("Usuario ID: " + asignacion.usuarioId());
            System.out.println("Celular ID: " + asignacion.celularId());
            System.out.println("Estado: " + asignacion.estado());
            System.out.println("---");
        }
        
        return asignaciones.stream()
            .map(asignacionMapper::toResponse)
            .collect(Collectors.toList());
    }
}