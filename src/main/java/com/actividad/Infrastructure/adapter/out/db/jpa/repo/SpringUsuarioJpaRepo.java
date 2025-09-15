/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.actividad.Infrastructure.adapter.out.db.jpa.repo;

import com.actividad.Infrastructure.adapter.out.db.jpa.entity.UsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUsuarioJpaRepo extends JpaRepository<UsuarioJpa, String> { }
