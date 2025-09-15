/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.service;

import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.policy.Hechos;
import com.actividad.Domain.policy.PoliticaAsignacion;
import com.actividad.Domain.valueobject.Email;
import com.actividad.Domain.valueobject.Imei;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ServicioAsignacionDeCelularTest {

  static PoliticaAsignacion politicaSiempreOk = new PoliticaAsignacion() {
    public boolean cumple(Usuario u, Celular c, Hechos h) { return true; }
    public String motivo() { return ""; }
  };

  static PoliticaAsignacion politicaFalla = new PoliticaAsignacion() {
    public boolean cumple(Usuario u, Celular c, Hechos h) { return false; }
    public String motivo() { return "No cumple"; }
  };

  @Test
  void permite_asignar_si_todas_las_politicas_pasan() {
    var servicio = new ServicioAsignacionDeCelular(List.of(politicaSiempreOk));
    var u = Usuario.create("usr-1", "Ana", Email.of("ana@ex.com"));
    var c = Celular.registrar("cel-1", "ACME", Imei.of("490154203237518"), true, true, "Op", "4G", 1);

    var res = servicio.puedeAsignar(u, c, new Hechos(false, 0, 2));
    assertThat(res.permitida()).isTrue();
  }

  @Test
  void rechaza_asignar_si_alguna_politica_falla() {
    var servicio = new ServicioAsignacionDeCelular(List.of(politicaFalla));
    var u = Usuario.create("usr-1", "Ana", Email.of("ana@ex.com"));
    var c = Celular.registrar("cel-1", "ACME", Imei.of("490154203237518"), true, true, "Op", "4G", 1);

    var res = servicio.puedeAsignar(u, c, new Hechos(false, 0, 2));
    assertThat(res.permitida()).isFalse();
    assertThat(res.motivo()).contains("No cumple");
  }
}
