/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.business;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cfoch
 */
@Entity
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String direccion;
    private double cooXDireccion;
    private double cooYDireccion;
    private String descripcion;
    private double capacidad;
    @NotNull
    private boolean activo;
    @OneToMany(mappedBy="id.tienda")
    private Set<CapacidadTienda> capacidadTiendas = new HashSet<CapacidadTienda>();
    @OneToMany(mappedBy = "tienda")
    private Set<Usuario> empleadosTienda = new HashSet<Usuario>();
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
    public double getCooXDireccion() {
        return cooXDireccion;
    }

    /**
     * @param cooXDireccion the cooXDireccion to set
     */
    public void setCooXDireccion(double cooXDireccion) {
        this.cooXDireccion = cooXDireccion;
    }

    /**
     * @return the cooYDireccion
     */
    public double getCooYDireccion() {
        return cooYDireccion;
    }

    /**
     * @param cooYDireccion the cooYDireccion to set
     */
    public void setCooYDireccion(double cooYDireccion) {
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
    public double getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(double capacidad) {
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

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the empleadosTienda
     */
    public Set<Usuario> getEmpleadosTienda() {
        return empleadosTienda;
    }

    /**
     * @param empleadosTienda the empleadosTienda to set
     */
    public void setEmpleadosTienda(Set<Usuario> empleadosTienda) {
        this.empleadosTienda = empleadosTienda;
    }
}
