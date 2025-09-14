/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.rest.request;

/**
 * Request opcional para devolver un celular.
 * Si tu endpoint usa solo el PathVariable (asignacionId), este request puede quedar vacío.
 * Mantengo un campo libre por si quieres auditar el motivo en el futuro.
 */
public record DevolverCelularRequest(String motivo) {
    public DevolverCelularRequest() { this(null); } // permite body vacío {}
}
