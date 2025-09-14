/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.repo;

import infrastructure.adapter.out.db.jpa.entity.CelularJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringCelularJpaRepo extends JpaRepository<CelularJpa, String> {

    @Query("""
       select case when count(a)>0 then true else false end
       from AsignacionJpa a join CelularJpa c on a.celularId = c.celularId
       where a.estado='ASIGNADO' and c.imei = ?1
    """)
    boolean existsImeiAsignado(String imei);
}
