/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.in;
import com.actividad.Domain.entity.Usuario;

public interface LoginUseCase {
    Usuario login(String emailOrId, String passwordPlain);
}
