/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.entity;

import domain.exception.DomainException;
import domain.valueobject.Imei;
import java.util.Objects;

public final class Celular {
    private final String celularId;   // identidad estable
    private String marca;
    private Imei imei;                // VO
    private boolean nfc;
    private boolean huella;
    private String operador;
    private String tecnologiaBanda;
    private int cantidadSim;

    private Celular(String celularId, String marca, Imei imei,
                    boolean nfc, boolean huella,
                    String operador, String tecnologiaBanda, int cantidadSim){
        if (celularId == null || celularId.isBlank()) throw DomainException.required("CelularId");
        if (marca == null || marca.isBlank()) throw DomainException.required("Marca");
        if (operador == null || operador.isBlank()) throw DomainException.required("Operador");
        if (tecnologiaBanda == null || tecnologiaBanda.isBlank()) throw DomainException.required("TecnologiaBanda");
        if (cantidadSim <= 0) throw DomainException.business("CantidadSim debe ser >= 1");

        this.celularId = celularId;
        this.marca = marca.trim();
        this.imei = imei;
        this.nfc = nfc;
        this.huella = huella;
        this.operador = operador.trim();
        this.tecnologiaBanda = tecnologiaBanda.trim();
        this.cantidadSim = cantidadSim;
    }

    public static Celular registrar(String celularId, String marca, Imei imei,
                                    boolean nfc, boolean huella,
                                    String operador, String tecnologiaBanda, int cantidadSim){
        return new Celular(celularId, marca, imei, nfc, huella, operador, tecnologiaBanda, cantidadSim);
    }

    public String id(){ return celularId; }
    public Imei imei(){ return imei; }
    public boolean tieneNfc(){ return nfc; }
    public boolean tieneHuella(){ return huella; }
    public String operador(){ return operador; }
    public String banda(){ return tecnologiaBanda; }
    public int cantidadSim(){ return cantidadSim; }

    public void actualizarOperador(String nuevoOperador, String nuevaBanda){
        if (nuevoOperador == null || nuevoOperador.isBlank()) throw DomainException.required("Operador");
        if (nuevaBanda == null || nuevaBanda.isBlank()) throw DomainException.required("TecnologiaBanda");
        // aquí podrías validar compatibilidad operador-banda
        this.operador = nuevoOperador.trim();
        this.tecnologiaBanda = nuevaBanda.trim();
    }

    public void reemplazarImei(Imei nuevo){ this.imei = nuevo; }

    @Override public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Celular)) return false;
        return celularId.equals(((Celular)o).celularId);
    }
    @Override public int hashCode(){ return Objects.hash(celularId); }
}
