package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.Entity.ClienteEntity;
import com.Docdelivery.Backend.Service.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController()
@RequestMapping("/customers")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping("/moreSpent")
    @Secured({"ROLE_ADMIN"})// se asume que el admin es quien puede acceder a esta consulta
    public Optional<ClienteEntity> moreSpent() {
        try{
            Optional<ClienteEntity> cliente = clienteServices.obtenerClienteMasAGastado();
            return cliente;
        }catch(Exception e){
            return Optional.empty();
        }
    }
}
