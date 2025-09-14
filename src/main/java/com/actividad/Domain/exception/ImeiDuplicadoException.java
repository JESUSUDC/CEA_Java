/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.exception;

/**
 *
 * @author Jesus
 */
public class ImeiDuplicadoException extends DomainException {
    public ImeiDuplicadoException(String imei) {
        super("IMEI ya activo: " + imei);
    }
}
