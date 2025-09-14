/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.repo;

import infrastructure.adapter.out.db.jpa.entity.AsignacionJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringAsignacionJpaRepo extends JpaRepository<AsignacionJpa, String> {
    @Query("select count(a) from AsignacionJpa a where a.usuarioId = ?1 and a.estado = 'ASIGNADO'")
    int countActivosPorUsuario(String usuarioId);
}