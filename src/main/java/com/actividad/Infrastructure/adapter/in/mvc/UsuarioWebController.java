package com.actividad.Infrastructure.adapter.in.mvc;

import com.actividad.Application.port.in.UsuarioCrudUseCase;
import com.actividad.Application.dto.command.CreateUsuarioCommand;
import com.actividad.Application.dto.command.UpdateUsuarioCommand;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Email;
import com.actividad.Domain.valueObject.Password;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioWebController {
    private final UsuarioCrudUseCase userCase;

    public UsuarioWebController(UsuarioCrudUseCase userCase) {
        this.userCase = userCase;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userCase.findAll());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String formularioRegistro(Model model) {
        model.addAttribute("usuarioForm", new UsuarioForm());
        return "usuario-form";
    }

    @PostMapping
    public String guardarUsuario(@ModelAttribute UsuarioForm usuarioForm) {
        userCase.create(new CreateUsuarioCommand(
            usuarioForm.getNombre(),
            usuarioForm.getEmail(),
            usuarioForm.getPassword()
        ));
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        var usuario = userCase.findById(id);
        var form = new UsuarioForm();
        form.setId(usuario.id());
        form.setNombre(usuario.nombre());
        form.setEmail(usuario.email().value());
        // No incluimos la contraseña por seguridad
        model.addAttribute("usuarioForm", form);
        return "usuario-form";
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute UsuarioForm usuarioForm) {
        userCase.update(new UpdateUsuarioCommand(
            usuarioForm.getId(),
            usuarioForm.getNombre(),
            usuarioForm.getEmail()
        ));
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        userCase.delete(id);
        return "redirect:/usuarios";
    }

    // Clase interna para el formulario
    public static class UsuarioForm {
        private String id;
        private String nombre;
        private String email;
        private String password;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
