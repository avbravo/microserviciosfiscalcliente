/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.microserviciosfiscalcliente;

import com.avbravo.microserviciosfiscalcliente.client.RestClient;
import com.avbravo.microserviciosfiscalcliente.entity.Factura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author avbravo
 */
@Named
@ViewScoped
public class FacturaController implements Serializable {
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
//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget
//                = client.target("http://192.168.0.4:8080/microservicesfiscalsqlserver-0.2/resources/factura");
//        //    facturaList = service.createCars(10);

//        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//        
//        Response response  = invocationBuilder.get(Factura.class);
//
//Response responsePost   = invocationBuilder.post(Entity.entity(employee, MediaType.APPLICATION_JSON);
//        Factura factura = new Factura(6, 147);

//        Response response = client.createJsonFactura(factura);
//        if (response.getStatus() == HTTP_CREATED) {
//            System.out.println("== Se creo la factura");
//        }

Client client = ClientBuilder.newClient().register(new JacksonFeature());
List<Factura> list = client
.target("http://localhost:8080/microservicesfiscalsqlserver-0.2/resources/factura")
.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Factura>>() {
});

facturaList = list;
       // facturaList.add(new Factura(1, 15));
        System.out.println("-------------------> init" + facturaList.size());
    }

    
    public String save(){
        try {
             Response response = client.createJsonFactura(factura);
        if (response.getStatus() == HTTP_CREATED) {
            messages.info("Factura guardada");
            
        }else{
            messages.warn("No se creo la factura");
        }

        } catch (Exception e) {
            messages.error(e.getLocalizedMessage());
            
        }
        return "";
    }
}
