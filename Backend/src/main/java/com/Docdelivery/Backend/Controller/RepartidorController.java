// src/main/java/com/Docdelivery/Backend/Controller/RepartidorController.java
package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.dto.TopRepartidorDto;
import com.Docdelivery.Backend.Service.RepartidorService;
import com.Docdelivery.Backend.dto.VistaRepartidorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {

    @Autowired
    private RepartidorService repartidorService;

    @GetMapping("/top3")
    public ResponseEntity<List<TopRepartidorDto>> getTop3Repartidores() {
        List<TopRepartidorDto> topRepartidores = repartidorService.findTop3ByRendimiento();
        return ResponseEntity.ok(topRepartidores);
    }

    //Llamada a la vista de repartidores
    @GetMapping("/desempeno")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<VistaRepartidorDto>> obtenerDesempeno() {
        return ResponseEntity.ok(repartidorService.obtenerDesempeno());
    }

}