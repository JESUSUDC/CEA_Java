package com.actividad.Application.usecase;

import com.actividad.Application.port.out.*;
import com.actividad.Domain.aggregate.AsignacionDeCelular;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.event.DomainEvent;
import com.actividad.Domain.policy.Hechos;
import com.actividad.Domain.policy.PoliticaAsignacion;
import com.actividad.Domain.service.ServicioAsignacionDeCelular;
import com.actividad.Domain.valueObject.Email;
import com.actividad.Domain.valueObject.Imei;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AsignarCelularServiceTest {

  UsuarioRepositoryPort usuarios = mock(UsuarioRepositoryPort.class);
  CelularRepositoryPort celulares = mock(CelularRepositoryPort.class);
  AsignacionRepositoryPort asignaciones = mock(AsignacionRepositoryPort.class);
  DomainEventPublisherPort publisher = mock(DomainEventPublisherPort.class);

  ServicioAsignacionDeCelular validador;

  @BeforeEach
  void setup() {
    PoliticaAsignacion politicaUnicidadImei = new PoliticaAsignacion() {
      private String motivo = "";
      @Override public boolean cumple(Usuario u, Celular c, Hechos h) {
        if (h.imeiDuplicado()) { motivo = "IMEI duplicado"; return false; }
        return true;
      }
      @Override public String motivo() { return motivo; }
    };

    PoliticaAsignacion politicaLimitePorUsuario = new PoliticaAsignacion() {
      private String motivo = "";
      @Override public boolean cumple(Usuario u, Celular c, Hechos h) {
        if (h.activosUsuario() >= h.maxActivos()) { motivo = "Límite"; return false; }
        return true;
      }
      @Override public String motivo() { return motivo; }
    };

    PoliticaAsignacion politicaCapacidades = new PoliticaAsignacion() {
      private String motivo = "";
      @Override public boolean cumple(Usuario u, Celular c, Hechos h) {
        if (!c.tieneNfc() || !c.tieneHuella()) { motivo = "Capacidades"; return false; }
        return true;
      }
      @Override public String motivo() { return motivo; }
    };

    validador = new ServicioAsignacionDeCelular(
        List.of(politicaUnicidadImei, politicaLimitePorUsuario, politicaCapacidades)
    );
  }

  @Test
  void asigna_y_publica_evento() {
    var u = Usuario.create("usr-1", "Ana", Email.of("ana@ex.com"));
    var c = Celular.registrar("cel-1", "ACME", Imei.of("490154203237518"),
                              true, true, "Op", "4G", 1);

    when(usuarios.findById("usr-1")).thenReturn(Optional.of(u));
    when(celulares.findById("cel-1")).thenReturn(Optional.of(c));
    when(celulares.existeImeiActivo("490154203237518")).thenReturn(false);
    when(asignaciones.activosPorUsuario("usr-1")).thenReturn(0);

    var useCase = new AsignarCelularService(usuarios, celulares, asignaciones, publisher, validador);
    var id = useCase.asignar("usr-1", "cel-1");

    assertThat(id).isNotBlank();

    verify(asignaciones).save(any(AsignacionDeCelular.class));

    @SuppressWarnings("unchecked")
    ArgumentCaptor<List<DomainEvent>> eventsCaptor = ArgumentCaptor.forClass((Class) List.class);
    verify(publisher).publish(eventsCaptor.capture());
    assertThat(eventsCaptor.getValue()).isNotEmpty();
  }
}
