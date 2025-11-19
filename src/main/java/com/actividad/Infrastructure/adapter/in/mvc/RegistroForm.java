package com.actividad.Infrastructure.adapter.in.mvc;

public class RegistroForm {
    private String nombre;
    private String email;
    private String password;
    
    // Getters y Setters REQUERIDOS
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}