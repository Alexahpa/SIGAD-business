/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cfoch
 */
@Entity
//@Table("capacidadtienda")
public class CapacidadTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Integer cantidad;
    @ManyToOne
    private Insumo insumo;
    @ManyToOne
    private Tienda tienda;

    /**
     * Constructor.
     */
    public CapacidadTienda() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the Tienda
     */
    public Tienda getTienda() {
        return tienda;
    }

    /**
     * @param tienda the Tienda to set
     */
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    /**
     * @return the insumo
     */
    public Insumo getInsumo() {
        return insumo;
    }

    /**
     * @param insumo the insumo to set
     */
    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
}
