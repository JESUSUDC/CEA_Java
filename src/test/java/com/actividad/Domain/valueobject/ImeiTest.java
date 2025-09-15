/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.valueObject;

import com.actividad.Domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ImeiTest {

  @Test
  void crea_imei_valido_y_preserva_valor() {
    var imei = Imei.of("490154203237518"); // válido por Luhn
    assertThat(imei.value()).isEqualTo("490154203237518");
  }

  @Test
  void falla_en_formato_invalido() {
    assertThatThrownBy(() -> Imei.of("123")) // muy corto
        .isInstanceOf(DomainException.class);
  }

  @Test
  void igualdad_por_valor() {
    var a = Imei.of("490154203237518");
    var b = Imei.of("490154203237518");
    assertThat(a).isEqualTo(b).hasSameHashCodeAs(b);
  }
}
