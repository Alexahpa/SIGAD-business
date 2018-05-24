/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.repository;

import com.sigad.sigad.business.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alexandra
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
}
