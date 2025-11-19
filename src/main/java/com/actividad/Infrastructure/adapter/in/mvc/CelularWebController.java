package com.actividad.Infrastructure.adapter.in.mvc;

import com.actividad.Application.port.in.CelularCrudUseCase;
import com.actividad.Application.dto.command.CreateCelularCommand;
import com.actividad.Application.dto.command.UpdateCelularCommand;
import com.actividad.Domain.entity.Celular;
import com.actividad.Domain.valueObject.Imei;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import com.actividad.Domain.entity.Usuario;
import com.actividad.Domain.valueObject.Password;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.actividad.Domain.exception.DomainException;


@Controller
@RequestMapping("/celulares")
public class CelularWebController {
    private final CelularCrudUseCase celularService;

    public CelularWebController(CelularCrudUseCase celularService) {
        this.celularService = celularService;
    }

    @GetMapping
    public String listarCelulares(Model model) {
        model.addAttribute("celulares", celularService.findAll());
        return "celulares";
    }

    @GetMapping("/nuevo")
    public String nuevoCelularForm(Model model) {
        if (!model.containsAttribute("celularForm")) {
            model.addAttribute("celularForm", new CelularForm());
        }
        return "celular-form";
    }

    @PostMapping
    public String guardarCelular(@ModelAttribute CelularForm celularForm, 
                               RedirectAttributes redirectAttributes) {
        try {
            celularService.create(new CreateCelularCommand(
                celularForm.getMarca(),
                celularForm.getImei(),
                celularForm.isNfc(),
                celularForm.isHuella(),
                celularForm.getOperador(),
                celularForm.getTecnologiaBanda(),
                celularForm.getCantidadSim()
            ));
            redirectAttributes.addFlashAttribute("success", "Celular registrado exitosamente");
            return "redirect:/celulares";
        } catch (DomainException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("celularForm", celularForm);
            return "redirect:/celulares/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el celular: " + e.getMessage());
            redirectAttributes.addFlashAttribute("celularForm", celularForm);
            return "redirect:/celulares/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarCelular(@PathVariable String id, Model model) {
        try {
            var celular = celularService.findById(id);
            var form = new CelularForm();
            form.setId(celular.id());
            form.setMarca(celular.marca());
            form.setImei(celular.imei().value());
            form.setNfc(celular.tieneNfc());
            form.setHuella(celular.tieneHuella());
            form.setOperador(celular.operador());
            form.setTecnologiaBanda(celular.banda());
            form.setCantidadSim(celular.cantidadSim());
            model.addAttribute("celularForm", form);
            return "celular-form";
        } catch (Exception e) {
            return "redirect:/celulares?error=Celular no encontrado";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarCelular(@ModelAttribute CelularForm celularForm,
                                  RedirectAttributes redirectAttributes) {
        try {
            celularService.update(new UpdateCelularCommand(
                celularForm.getId(),
                celularForm.getMarca(),
                celularForm.getOperador(),
                celularForm.getTecnologiaBanda(),
                celularForm.getCantidadSim()
            ));
            redirectAttributes.addFlashAttribute("success", "Celular actualizado exitosamente");
            return "redirect:/celulares";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el celular: " + e.getMessage());
            return "redirect:/celulares/editar/" + celularForm.getId();
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCelular(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            celularService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Celular eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el celular: " + e.getMessage());
        }
        return "redirect:/celulares";
    }

    // Clase DTO para el formulario
    public static class CelularForm {
        private String id;
        private String marca;
        private String imei;
        private boolean nfc;
        private boolean huella;
        private String operador;
        private String tecnologiaBanda;
        private int cantidadSim = 1;

        // Getters y setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getMarca() { return marca; }
        public void setMarca(String marca) { this.marca = marca; }
        public String getImei() { return imei; }
        public void setImei(String imei) { this.imei = imei; }
        public boolean isNfc() { return nfc; }
        public void setNfc(boolean nfc) { this.nfc = nfc; }
        public boolean isHuella() { return huella; }
        public void setHuella(boolean huella) { this.huella = huella; }
        public String getOperador() { return operador; }
        public void setOperador(String operador) { this.operador = operador; }
        public String getTecnologiaBanda() { return tecnologiaBanda; }
        public void setTecnologiaBanda(String tecnologiaBanda) { this.tecnologiaBanda = tecnologiaBanda; }
        public int getCantidadSim() { return cantidadSim; }
        public void setCantidadSim(int cantidadSim) { this.cantidadSim = cantidadSim; }
    }
}