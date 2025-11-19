/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.in.mvc;

import org.springframework.stereotype.Controller;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Optional;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Password;


@Controller
public class MainWebController {

    @GetMapping("/")
    public String inicio() {
        return "home";  // home.html
    }

    /*@GetMapping("/login")
    public String login(Model model) {
        return "login"; // login.html tendrá link a registro
    }*/

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("registroForm", new RegistroForm());
        return "registro"; // registro.html para formulario de registro
    }
}

