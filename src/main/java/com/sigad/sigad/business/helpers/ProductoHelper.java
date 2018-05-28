/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.business.helpers;

import com.sigad.sigad.app.controller.LoginController;
import com.sigad.sigad.business.Producto;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Alexandra
 */
public class ProductoHelper {

    Session session = null;
    
    public ProductoHelper() {
        session = LoginController.serviceInit();
        session.beginTransaction();
        // Add new Employee object
//        Producto prod = new Producto("Rosas", "/images/rosa.jpg", 15, 12.0,Boolean.TRUE);
//        Producto prod2 = new Producto("Chocolates", "/images/chocolate.jpg", 15,12.0, Boolean.TRUE);
//        session.save(prod);
//        session.save(prod2);
    }
    
    public ArrayList<Producto> getProducts(){
        ArrayList<Producto> list = null;
        try {
            Query query  = session.createQuery("from Producto");
            
            if(!query.list().isEmpty()){
                list= (ArrayList<Producto>) query.list();
            }
        } catch (Exception e) {
        }
        
        return list;
    };

    public void close(){
        session.getTransaction().commit();
    }
}