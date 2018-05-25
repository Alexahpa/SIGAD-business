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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cfoch
 */
@Table(name="clientedireccion")
@Entity
public class ClienteDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String direccionCliente;
    private String nombreDireccion;
    @NotNull
    private Boolean principal;
    @NotNull
    private Boolean activo;
    @NotNull
    private Double cooXDireccion;
    @NotNull
    private Double cooYDireccion;
    @ManyToOne(optional=false)
    private Usuario usuario;

    /**
     * Constructor.
     */
    public ClienteDireccion() {
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
     * @return the direccionCliente
     */
    public String getDireccionCliente() {
        return direccionCliente;
    }

    /**
     * @param direccionCliente the direccionCliente to set
     */
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    /**
     * @return the nombreDireccion
     */
    public String getNombreDireccion() {
        return nombreDireccion;
    }

    /**
     * @param nombreDireccion the nombreDireccion to set
     */
    public void setNombreDireccion(String nombreDireccion) {
        this.nombreDireccion = nombreDireccion;
    }

    /**
     * @return the principal
     */
    public Boolean isPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    /**
     * @return the activo
     */
    public Boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
