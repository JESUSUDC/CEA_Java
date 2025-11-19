/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

import com.actividad.Application.port.in.AuthUseCase;
import com.actividad.Application.port.out.UsuarioRepositoryPort;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.exception.DomainException;
import com.actividad.Infrastructure.adapter.out.jwt.JwtProvider;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {
    private final UsuarioRepositoryPort usuarios;
    //private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public AuthService(UsuarioRepositoryPort usuarios, /*PasswordEncoder encoder,*/ JwtProvider jwtProvider) {
        this.usuarios = usuarios;
        //this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String login(String email, String passwordPlain) {
        Usuario u = usuarios.findByEmail(email)
            .orElseThrow(() -> new DomainException("Credenciales inválidas"));

        /*if (!encoder.matches(passwordPlain, u.password().value()))
            throw new DomainException("Credenciales inválidas");*/

        return jwtProvider.generateToken(u.id());
    }
}
