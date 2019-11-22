/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.microserviciosfiscalcliente.entity;

/**
 *
 * @author avbravo
 */
public class Factura {
    private Integer idfactura;
    private Integer estado;

    public Factura(Integer idfactura, Integer estado) {
        this.idfactura = idfactura;
        this.estado = estado;
    }

    public Factura() {
    }

    
    
    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    
    
  
}
