/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Application.port.out;

import com.actividad.Domain.event.DomainEvent;
import java.util.List;

public interface DomainEventPublisherPort {
    void publish(List<DomainEvent> events);
}