/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Password;
import com.actividad.Application.port.in.UsuarioCrudUseCase;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthWebController {

    private final UsuarioCrudUseCase userCase;
    //private final PasswordEncoder passwordEncoder;

    public AuthWebController(UsuarioCrudUseCase userCase/*, PasswordEncoder passwordEncoder*/) {
        this.userCase = userCase;
        //this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                          @RequestParam(value = "logout", required = false) String logout,
                          Model model) {
        
        if (error != null) {
            model.addAttribute("error", "Credenciales inválidas. Usa: admin / admin123");
        }
        
        if (logout != null) {
            model.addAttribute("message", "Has cerrado sesión exitosamente.");
        }
        
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "redirect:/login?logout"; // Spring Security gestiona el logout real
    }

    @PostMapping("/recordar-password")
    public String recordatorio(@RequestParam String email, Model model) {
        Optional<Usuario> usuario = userCase.findByEmail(email); // Suponiendo este método
        if (usuario.isPresent()) {
            // Avanza al formulario de nueva contraseña
            model.addAttribute("email", email);
            return "redirect:/recordar-password?recuperar&email=" + email;
        } else {
            // Muestra error
            return "redirect:/recordar-password?error";
        }
    }

    @PostMapping("/actualizar-password")
    public String actualizarPassword(@RequestParam String email, @RequestParam String newpass) {
        Optional<Usuario> usuario = userCase.findByEmail(email); // Suponiendo este método
        if (usuario.isPresent()) {
            //usuario.get().changePassword(Password.of(passwordEncoder.encode(newpass)));
            usuario.get().changePassword(Password.of(newpass));
            userCase.updatePassword(usuario.get());
            return "redirect:/login?reset";
        }
        return "redirect:/recordar-password?error";
    }

}

