/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.usecase;

/*import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.actividad.Application.port.out.UsuarioRepositoryPort;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepositoryPort usuarioRepo;

    public CustomUserDetailsService(UsuarioRepositoryPort usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepo.findByEmail(email)
            .map(u -> org.springframework.security.core.userdetails.User
                .withUsername(u.email().value())
                .password(u.password().value()) // Spring Security toma este hash para comparar
                .roles("USER")
                .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }
}*/
