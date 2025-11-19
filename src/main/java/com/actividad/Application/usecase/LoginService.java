/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.LoginUseCase;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Domain.exception.DomainException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class LoginService implements LoginUseCase {
    private final UsuarioRepositoryPort usuarios;
    //private final PasswordEncoder encoder; // DECLARA AQUÍ EL ENCODER
    
    public LoginService(UsuarioRepositoryPort usuarios/*, PasswordEncoder encoder*/) { // RECIBE EN CONSTRUCTOR
        this.usuarios = usuarios;
        //this.encoder = encoder;
    }

    @Override
    public Usuario login(String email, String passwordPlain) {
        Usuario u = usuarios.findByEmail(email)
            .orElseThrow(() -> new DomainException("Credenciales inválidas"));

        // Aquí debería hacerse el hash en adapter, compara contra VO Password
        /*if (!encoder.matches(passwordPlain, u.password().value()))
            throw new DomainException("Credenciales inválidas");*/

        return u;
    }
}