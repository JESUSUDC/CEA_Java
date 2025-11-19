/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) { super(message); }

    public static DomainException required(String field) {
        return new DomainException("Falta " + field);
    }

    public static DomainException business(String message) {
        return new DomainException(message);
    }
}