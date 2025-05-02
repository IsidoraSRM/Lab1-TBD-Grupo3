package com.Alvolante.Backend.Entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private Long idPedido;
    private Long clienteId;
    private Long repartidorId;
    private Long medioPagoId;
    private LocalDateTime fechaPedido;
    private String estadoPedido;
    private String prioridadPedido;
}