package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.Service.ClienteServices;
import com.Docdelivery.Backend.dto.ClienteConTotalGastadoDTO; // Asegúrate de importar el DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping("/moreSpent")
    @Secured({"ROLE_ADMIN"}) // Se asume que el admin es quien puede acceder a esta consulta
    public Optional<ClienteConTotalGastadoDTO> moreSpent() {
        try {
            Optional<ClienteConTotalGastadoDTO> cliente = clienteServices.obtenerClienteMasAGastado();
            return cliente;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    @GetMapping("/{usuarioId}")
    @Secured({"ROLE_CLIENTE", "ROLE_ADMIN", "ROLE_TRABAJADOR"})
    public Object findClienteIdByUsuarioId(@PathVariable Long usuarioId) {
        return clienteServices.findClienteIdByUsuarioId(usuarioId);
    }
}
