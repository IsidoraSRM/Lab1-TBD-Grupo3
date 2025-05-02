package com.Alvolante.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEntity {
	Long idPago;
	Long idPedido;
	Long idMedioDePago;
	BigDecimal monto;
}
