package com.actividad.Infrastructure.adapter.in.mvc;

import com.actividad.Application.port.in.AsignarCelularUseCase;
import com.actividad.Application.port.in.DevolverCelularUseCase;
import com.actividad.Application.port.in.UsuarioCrudUseCase;
import com.actividad.Application.port.in.CelularCrudUseCase;
import com.actividad.Application.port.in.ListarAsignacionesUseCase;
import com.actividad.Application.dto.response.AsignacionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/asignaciones")
public class AsignacionWebController {
    private final AsignarCelularUseCase asignarUC;
    private final DevolverCelularUseCase devolverUC;
    private final UsuarioCrudUseCase usuarioUC;
    private final CelularCrudUseCase celularUC;
    private final ListarAsignacionesUseCase listarAsignacionesUC;

    public AsignacionWebController(AsignarCelularUseCase asignarUC, 
                                 DevolverCelularUseCase devolverUC,
                                 UsuarioCrudUseCase usuarioUC,
                                 CelularCrudUseCase celularUC,
                                 ListarAsignacionesUseCase listarAsignacionesUC) {
        this.asignarUC = asignarUC;
        this.devolverUC = devolverUC;
        this.usuarioUC = usuarioUC;
        this.celularUC = celularUC;
        this.listarAsignacionesUC = listarAsignacionesUC;
    }

    @GetMapping
    public String listarAsignaciones(Model model) {
        try {
            List<AsignacionResponse> asignaciones = listarAsignacionesUC.findAll();
            model.addAttribute("asignaciones", asignaciones);
            return "asignaciones";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar las asignaciones: " + e.getMessage());
            model.addAttribute("asignaciones", List.of());
            return "asignaciones";
        }
    }

    @GetMapping("/nuevo")
    public String nuevaAsignacionForm(Model model) {
        try {
            model.addAttribute("usuarios", usuarioUC.findAll());
            model.addAttribute("celulares", celularUC.findAll());
            if (!model.containsAttribute("asignacionForm")) {
                model.addAttribute("asignacionForm", new AsignacionForm());
            }
            return "asignacion-form";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el formulario: " + e.getMessage());
            return "asignacion-form";
        }
    }

    @PostMapping
    public String asignarCelular(@ModelAttribute AsignacionForm asignacionForm,
                               RedirectAttributes redirectAttributes) {
        try {
            String asignacionId = asignarUC.asignar(
                asignacionForm.getUsuarioId(), 
                asignacionForm.getCelularId()
            );
            redirectAttributes.addFlashAttribute("success", 
                "Celular asignado exitosamente. ID: " + asignacionId);
            return "redirect:/asignaciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al asignar celular: " + e.getMessage());
            redirectAttributes.addFlashAttribute("asignacionForm", asignacionForm);
            return "redirect:/asignaciones/nuevo";
        }
    }

    @PostMapping("/{id}/devolver")
    public String devolverCelular(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            devolverUC.devolver(id);
            redirectAttributes.addFlashAttribute("success", 
                "Celular devuelto exitosamente");
            return "redirect:/asignaciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al devolver celular: " + e.getMessage());
            return "redirect:/asignaciones";
        }
    }

    // Clase DTO para el formulario
    public static class AsignacionForm {
        private String usuarioId;
        private String celularId;

        public String getUsuarioId() { return usuarioId; }
        public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
        public String getCelularId() { return celularId; }
        public void setCelularId(String celularId) { this.celularId = celularId; }
    }
}