/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.factory;

import domain.entity.Celular;
import domain.exception.DomainException;
import domain.valueobject.Imei;

public final class CelularFactory {
    private CelularFactory(){}

    public static Celular registrar(String celularId, String marca, String imeiRaw,
                                    boolean nfc, boolean huella,
                                    String operador, String banda, int sim) {
        if (sim <= 0) throw DomainException.business("CantidadSim >= 1");
        return Celular.registrar(celularId, marca, Imei.of(imeiRaw), nfc, huella, operador, banda, sim);
    }
}
