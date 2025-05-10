package com.Docdelivery.Backend.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Docdelivery.Backend.Entity.DetallePedidoEntity;
import com.Docdelivery.Backend.Entity.OrderEntity;
import com.Docdelivery.Backend.Service.OrderService;
import com.Docdelivery.Backend.dto.CrearPedidoDto;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/crear")
    @Secured({"ROLE_CLIENTE", "ROLE_ADMIN"})
    public ResponseEntity<?> crearPedido(@RequestBody CrearPedidoDto pedidoDto) {
        try {
        // Obtener la información de autenticación actual
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Registrar información detallada sobre la autenticación
        System.out.println("=== DIAGNÓSTICO DE AUTENTICACIÓN ===");
        System.out.println("¿Usuario autenticado? " + auth.isAuthenticated());
        System.out.println("Nombre de usuario: " + auth.getName());
        System.out.println("Tipo de principal: " + (auth.getPrincipal() != null ? auth.getPrincipal().getClass().getName() : "null"));
        System.out.println("Roles/Autoridades: " + auth.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(", ")));
        System.out.println("Roles requeridos: ROLE_CLIENTE, ROLE_ADMIN");
        System.out.println("¿Tiene ROLE_CLIENTE? " + auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE")));
        System.out.println("¿Tiene ROLE_ADMIN? " + auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        System.out.println("===================================");
        
        // Continuar con la lógica existente
        Long idPedido = orderService.crearPedido(pedidoDto);
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Pedido creado con éxito");
        response.put("idPedido", idPedido);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (AccessDeniedException e) {
        // Capturar específicamente errores de acceso denegado
        System.out.println("Acceso denegado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Acceso denegado: No tienes los roles necesarios (ROLE_CLIENTE o ROLE_ADMIN)");
    } catch (Exception e) {
        System.out.println("Error en crearPedido: " + e.getClass().getName() + " - " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el pedido: " + e.getMessage());
    }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(@PathVariable Long id) {
        Optional<OrderEntity> pedido = orderService.getPedidoById(id);
        if (pedido.isPresent()) {
            OrderEntity order = pedido.get();
            List<DetallePedidoEntity> detalles = orderService.getDetallesByPedidoId(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("pedido", order);
            response.put("detalles", detalles);
            
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    //Procedure registerOrder
    @PostMapping("/register")
    @Secured({"ROLE_CLIENTE", "ROLE_ADMIN"})
    public int registerOrder(
            @RequestParam int clienteId,
            @RequestParam int empresaId,
            @RequestParam String prioridad
    ) {
        return orderService.registerOrder(clienteId, empresaId, prioridad);
    }


    //Procedimiento para cambiar el estado de un pedido
    @PutMapping("/{id}/estado")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> cambiarEstadoPedido(
            @PathVariable("id") Long idPedido,
            @RequestParam("estado") String nuevoEstado) {

        try {
            orderService.cambiarEstadoPedido(idPedido, nuevoEstado);
            return ResponseEntity.ok("Estado del pedido actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cambiar el estado del pedido: " + e.getMessage());
        }
    }

}
