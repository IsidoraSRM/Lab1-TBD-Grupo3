package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.Service.DetallePedidoService;
import com.Docdelivery.Backend.dto.ServiciosDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detallepedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/masPedidos")
    @Secured({"ROLE_CLIENTE", "ROLE_ADMIN"})
    public ResponseEntity<List<ServiciosDto>> getServiciosMasPedidos() {
        return ResponseEntity.ok(detallePedidoService.obtenerServiciosMasPedidos());
    }
}
