/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.aggregate;

import com.actividad.Domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AsignacionDeCelularTest {

  @Test
  void asignar_cumple_invariantes_y_emite_evento() {
    var ra = new AsignacionDeCelular("asig-1");
    ra.asignar(
        "usr-1", "cel-1",
        false,   // imeiDuplicado
        0, 2,    // activosUsuario, maxActivos
        true, true); // nfc, huella

    assertThat(ra.estado()).isEqualTo(AsignacionDeCelular.Estado.ASIGNADO);
    assertThat(ra.pullEvents()).hasSize(1);
  }

  @Test
  void asignar_falla_si_supera_limite() {
    var ra = new AsignacionDeCelular("asig-1");
    assertThatThrownBy(() -> ra.asignar("usr-1", "cel-1", false, 2, 2, true, true))
        .isInstanceOf(DomainException.class)
        .hasMessageContaining("Límite");
  }

  @Test
  void asignar_falla_por_imei_duplicado() {
    var ra = new AsignacionDeCelular("asig-1");
    assertThatThrownBy(() -> ra.asignar("usr-1", "cel-1", true, 0, 2, true, true))
        .isInstanceOf(DomainException.class);
  }
}