package com.Alvolante.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoEntity {
    public Long idDetallePedido;
    public Long pedidoId;
    public Long productoId;
    public Integer cantidad;
}
