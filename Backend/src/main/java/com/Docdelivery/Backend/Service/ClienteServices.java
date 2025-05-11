package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.Entity.ClienteEntity;
import com.Docdelivery.Backend.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    //consulta numero 1
    public Optional<ClienteEntity> obtenerClienteMasAGastado() {return clienteRepository.obtenerClienteQueMasHaGastado();}

}
