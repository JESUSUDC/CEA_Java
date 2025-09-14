/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.entity;

import jakarta.persistence.*;

@Entity @Table(name = "celulares")
public class CelularJpa {
    @Id
    private String celularId;
    private String marca;
    private String imei;
    private boolean nfc;
    private boolean huella;
    private String operador;
    private String tecnologiaBanda;
    private int cantidadSim;

    // getters & setters
    // ...
    public String getCelularId() { return celularId; }
    public void setCelularId(String celularId) { this.celularId = celularId; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getImei() { return imei; }
    public void setImei(String imei) { this.imei = imei; }
    public boolean isNfc() { return nfc; }
    public void setNfc(boolean nfc) { this.nfc = nfc; }
    public boolean isHuella() { return huella; }
    public void setHuella(boolean huella) { this.huella = huella; }
    public String getOperador() { return operador; }
    public void setOperador(String operador) { this.operador = operador; }
    public String getTecnologiaBanda() { return tecnologiaBanda; }
    public void setTecnologiaBanda(String tecnologiaBanda) { this.tecnologiaBanda = tecnologiaBanda; }
    public int getCantidadSim() { return cantidadSim; }
    public void setCantidadSim(int cantidadSim) { this.cantidadSim = cantidadSim; }
}
