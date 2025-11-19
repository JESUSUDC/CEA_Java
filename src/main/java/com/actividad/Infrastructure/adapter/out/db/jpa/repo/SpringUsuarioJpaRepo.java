/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.repo;

import com.actividad.Infrastructure.adapter.out.db.jpa.entity.UsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.time.Instant;


public interface SpringUsuarioJpaRepo extends JpaRepository<UsuarioJpa, String> {
    Optional<UsuarioJpa> findByEmail(String email);
}
