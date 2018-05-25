/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sigad.sigad.models.Producto;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Alexandra
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto , Long>{
     
}
