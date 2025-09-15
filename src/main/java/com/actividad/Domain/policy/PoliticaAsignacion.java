/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.policy;

import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.entity.Celular;

public interface PoliticaAsignacion {
    boolean cumple(Usuario u, Celular c, Hechos hechos);
    String motivo();
}
