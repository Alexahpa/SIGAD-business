/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigad.sigad.models;

import javax.validation.constraints.NotNull;
import javax.persistence.EmbeddedId;
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
@Table(name = "lotetienda")
public class LoteTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Tienda tienda;
    @OneToOne
    private LoteInsumo loteInsumo;

    @NotNull
    private Integer stock;

    /**
     * Constructor.
     */
    public LoteTienda() {
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
     * @return the stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.setStock(stock);
    }

    /**
     * @return the tenda
     */
    public Tienda getTienda() {
        return this.tienda;
    }

    /**
     * @param tienda the tenda to set
     */
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;

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
