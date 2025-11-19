/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.dto.command;

public record CreateCelularCommand(
    String marca,
    String imei,
    boolean nfc,
    boolean huella,
    String operador,
    String tecnologiaBanda,
    int cantidadSim
) {}