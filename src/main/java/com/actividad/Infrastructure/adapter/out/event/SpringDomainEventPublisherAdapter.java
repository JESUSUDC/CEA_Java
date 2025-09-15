/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.event;

import com.actividad.Application.port.out.DomainEventPublisherPort;
import com.actividad.Domain.event.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringDomainEventPublisherAdapter implements DomainEventPublisherPort {
    private final ApplicationEventPublisher publisher;
    public SpringDomainEventPublisherAdapter(ApplicationEventPublisher publisher){ this.publisher = publisher; }

    @Override public void publish(List<DomainEvent> events) {
        events.forEach(publisher::publishEvent);
        // Alternativa: serializar y enviar a Kafka/RabbitMQ aquí.
    }
}