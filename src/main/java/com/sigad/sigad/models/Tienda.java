/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cfoch
 */
@Entity
@Table(name="tienda")
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String direccion;
    private Double cooXDireccion;
    private Double cooYDireccion;
    private String descripcion;
    private Double capacidad;
    @OneToMany(mappedBy="tienda")
    private Set<CapacidadTienda> capacidadTiendas = new HashSet<CapacidadTienda>();

    /**
     * Constructor.
     */
    public Tienda() {
    }
    
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the cooXDireccion
     */
    public Double getCooXDireccion() {
        return cooXDireccion;
    }

    /**
     * @param cooXDireccion the cooXDireccion to set
     */
    public void setCooXDireccion(Double cooXDireccion) {
        this.cooXDireccion = cooXDireccion;
    }

    /**
     * @return the cooYDireccion
     */
    public Double getCooYDireccion() {
        return cooYDireccion;
    }

    /**
     * @param cooYDireccion the cooYDireccion to set
     */
    public void setCooYDireccion(Double cooYDireccion) {
        this.cooYDireccion = cooYDireccion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the capacidad
     */
    public Double getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Double capacidad) {
        this.capacidad = capacidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the capacidadTiendas
     */
    public Set<CapacidadTienda> getCapacidadTiendas() {
        return capacidadTiendas;
    }

    /**
     * @param capacidadTienda the capacidadTienda to add
     */
    public void addCapacidadTienda(CapacidadTienda capacidadTienda) {
        capacidadTiendas.add(capacidadTienda);
    }
}
