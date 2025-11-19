/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.in;


public interface AuthUseCase {
    String login(String email, String passwordPlain); // Retorna el JWT si login es correcto
}
