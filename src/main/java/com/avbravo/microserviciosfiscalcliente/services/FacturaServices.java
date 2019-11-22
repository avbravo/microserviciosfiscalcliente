/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.microserviciosfiscalcliente.services;

import com.avbravo.microserviciosfiscalcliente.Messages;
import com.avbravo.microserviciosfiscalcliente.entity.Factura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class FacturaServices implements Serializable{
    @Inject
    Messages messages;
    public List<Factura> findAll(){
        List<Factura> f= new ArrayList<>();
        try {
             Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/microservicesfiscalsqlserver-0.2/resources/factura");

            GenericType<List<Factura>> noticias = new GenericType<List<Factura>>() { };

            List<Factura> list = target.request(MediaType.APPLICATION_JSON).get(noticias);
            f=list;
        } catch (Exception e) {
            messages.error("findAll()");
        }
        return f;
    }
}
