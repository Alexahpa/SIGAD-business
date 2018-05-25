/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.service;

import com.sigad.sigad.app.controller.LoginController;
import com.sigad.sigad.models.Producto;
import com.sigad.sigad.repository.ProductoRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexandra
 */
@Service
public class ProductoService {

    private ProductoRepositorio repositorioProducto;
    Session session = null;
    @Autowired
    public ProductoService(ProductoRepositorio producto) {
        this.repositorioProducto = producto;
//        session = LoginController.serviceInit();
//        session.beginTransaction();
//        // Add new Employee object
//        Producto prod = new Producto("Rosas", "/images/rosa.jpg", 15, 12.0,Boolean.TRUE);
//        Producto prod2 = new Producto("Chocolates", "/images/chocolate.jpg", 15,12.0, Boolean.TRUE);
//        session.save(prod);
//        session.save(prod2);
    }
    
    public List<Producto> findAllProducto(){

        return repositorioProducto  .findAll();
    }

    public void close(){
        session.getTransaction().commit();
    }
}
