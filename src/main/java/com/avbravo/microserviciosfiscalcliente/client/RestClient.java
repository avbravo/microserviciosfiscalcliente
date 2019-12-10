/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.microserviciosfiscalcliente.client;

import com.avbravo.microserviciosfiscalcliente.entity.Factura;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
public class RestClient {

//    private static final String REST_URI = "http://192.168.0.4:8080/microservicesfiscalsqlserver-0.2/resources/factura";
    private static final String REST_URI = "http://localhost:8080/microservicesfiscalsqlserver-0.3/resources/factura";

    private Client client = ClientBuilder.newClient();

    public Factura getJsonFactura(int id) {
        return client
                .target(REST_URI)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .get(Factura.class);
    }

    public Response createJsonFactura(Factura factura) {
        return client
                .target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(factura, MediaType.APPLICATION_JSON));
    }
}
