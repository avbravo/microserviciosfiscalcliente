/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.microserviciosfiscalcliente;

import com.avbravo.microserviciosfiscalcliente.client.RestClient;
import com.avbravo.microserviciosfiscalcliente.entity.Factura;
import com.avbravo.microserviciosfiscalcliente.services.FacturaServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class FacturaController implements Serializable {
@Inject
FacturaServices facturaServices;
    @Inject
    Messages messages;
    public static final int HTTP_CREATED = 201;
    private RestClient client = new RestClient();
    List<Factura> facturaList = new ArrayList<>();
    Factura factura = new Factura();

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @PostConstruct
    public void init() {
        try {

//            Client client = ClientBuilder.newClient();
//            WebTarget target = client.target("http://localhost:8080/microservicesfiscalsqlserver-0.2/resources/factura");
//
//            GenericType<List<Factura>> noticias = new GenericType<List<Factura>>() { };
//
//            List<Factura> list = target.request(MediaType.APPLICATION_JSON).get(noticias);
//            if (list == null || list.isEmpty()) {
//                messages.warn("No hay facturas");
//            } else {
//                facturaList = list;
//            }
           
            List<Factura> list = facturaServices.findAll();
            if (list == null || list.isEmpty()) {
                messages.warn("No hay facturas");
            } else {
                facturaList = list;
            }

            
        } catch (Exception e) {
            System.out.println("error() " + e.getLocalizedMessage());
        }
    }

    public String save() {
        try {
            Response response = client.createJsonFactura(factura);
            if (response.getStatus() == HTTP_CREATED) {
                messages.info("Factura guardada");

            } else {
                messages.warn("No se creo la factura");
            }

        } catch (Exception e) {
            messages.error(e.getLocalizedMessage());

        }
        return "";
    }
}
