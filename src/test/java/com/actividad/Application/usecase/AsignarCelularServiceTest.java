/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.out.*;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.event.DomainEvent;
import com.actividad.Domain.policy.Hechos;
import com.actividad.Domain.service.ServicioAsignacionDeCelular;
import com.actividad.Domain.valueobject.Email;
import com.actividad.Domain.valueobject.Imei;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsignarCelularServiceTest {

  UsuarioRepositoryPort usuarios = mock(UsuarioRepositoryPort.class);
  CelularRepositoryPort celulares = mock(CelularRepositoryPort.class);
  AsignacionRepositoryPort asignaciones = mock(AsignacionRepositoryPort.class);
  DomainEventPublisherPort publisher = mock(DomainEventPublisherPort.class);

  ServicioAsignacionDeCelular validador;

  @BeforeEach
  void setup() {
    validador = new ServicioAsignacionDeCelular(List.of(
        // políticas reales si quieres; para test unitario, podrían ser stubs
        (u, c, h) -> !h.imeiDuplicado(), () -> "IMEI duplicado",
        (u, c, h) -> h.activosUsuario() < h.maxActivos(), () -> "Límite",
        (u, c, h) -> c.tieneNfc() && c.tieneHuella(), () -> "Capacidades"
    ));
  }

  @Test
  void asigna_y_publica_evento() {
    var u = Usuario.create("usr-1", "Ana", Email.of("ana@ex.com"));
    var c = Celular.registrar("cel-1", "ACME", Imei.of("490154203237518"), true, true, "Op", "4G", 1);

    when(usuarios.findById("usr-1")).thenReturn(Optional.of(u));
    when(celulares.findById("cel-1")).thenReturn(Optional.of(c));
    when(celulares.existeImeiActivo("490154203237518")).thenReturn(false);
    when(asignaciones.activosPorUsuario("usr-1")).thenReturn(0);

    var useCase = new AsignarCelularService(usuarios, celulares, asignaciones, publisher, validador);
    var id = useCase.asignar("usr-1", "cel-1");

    assertThat(id).isNotBlank();

    // verificamos que guardó y publicó eventos
    verify(asignaciones).save(any(AsignacionDeCelular.class));
    ArgumentCaptor<List<DomainEvent>> events = ArgumentCaptor.forClass(List.class);
    verify(publisher).publish(events.capture());
    assertThat(events.getValue()).isNotEmpty();
  }
}
