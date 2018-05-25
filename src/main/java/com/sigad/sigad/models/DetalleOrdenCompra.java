/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.models;

import javax.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author jorgeespinoza
 */
@Entity
@Table(name="detalleordencompra")
public class DetalleOrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull
    private Double precioDetalle;
    
    //debe existir una orden de compra
    @ManyToOne(optional = false)
    private OrdenCompra orden;
    @OneToOne
    private LoteInsumo loteInsumo;

    /**
     * Constructor.
     */
    public DetalleOrdenCompra() {
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the precioDetalle
     */
    public Double getPrecioDetalle() {
        return precioDetalle;
    }

    /**
     * @param precioDetalle the precioDetalle to set
     */
    public void setPrecioDetalle(Double precioDetalle) {
        this.precioDetalle = precioDetalle;
    }

    /**
     * @return the orden
     */
    public OrdenCompra getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(OrdenCompra orden) {
        this.orden = orden;
    }

    /**
     * @return the loteInsumo
     */
    public LoteInsumo getLoteInsumo() {
        return loteInsumo;
    }

    /**
     * @param loteInsumo the loteInsumo to set
     */
    public void setLoteInsumo(LoteInsumo loteInsumo) {
        this.loteInsumo = loteInsumo;
    }
    
}
