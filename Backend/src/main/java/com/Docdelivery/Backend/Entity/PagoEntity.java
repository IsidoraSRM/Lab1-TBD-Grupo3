package com.Docdelivery.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoEntity {
	Long idPago;
	// P oneToOne P(O)
	Long idPedido;
	// MP oneToOne P
	Long idMedioDePago;
	BigDecimal monto;

}
