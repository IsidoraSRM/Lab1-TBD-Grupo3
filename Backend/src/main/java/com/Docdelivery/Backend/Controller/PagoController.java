package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.Entity.PagoEntity;
import com.Docdelivery.Backend.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin
public class PagoController {

	@Autowired
	private PagoRepository pagoRepository;

	// Obtener todos los pagos
	@GetMapping
	public ResponseEntity<List<PagoEntity>> getAllPagos() {
		List<PagoEntity> pagos = pagoRepository.findAll();
		return ResponseEntity.ok(pagos);
	}

	// Obtener un pago por ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getPagoById(@PathVariable Long id) {
		Optional<PagoEntity> pago = pagoRepository.findById(id);
		if (pago.isPresent()) {
			return ResponseEntity.ok(pago.get());
		} else {
			return ResponseEntity.status(404).body("Pago no encontrado con ID: " + id);
		}
	}

	// Crear un nuevo pago
	@PostMapping
	public ResponseEntity<?> createPago(@RequestBody PagoEntity pago) {
		try {
			pagoRepository.save(pago);
			return ResponseEntity.status(201).body("Pago creado exitosamente");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error al crear el pago: " + e.getMessage());
		}
	}

	// Obtener el medio de pago más usado en pedidos urgentes
	@GetMapping("/mas-usado-en-urgentes")
	public ResponseEntity<?> getMedioDePagoMasUsadoEnUrgentes() {
		try {
			String metodoMasUsado = pagoRepository.findMedioDePagoMasUsadoEnUrgentes();
			if (metodoMasUsado != null && !metodoMasUsado.isEmpty()) {
				Map<String, String> response = new HashMap<>();
				response.put("medioDePago", metodoMasUsado);
				return ResponseEntity.ok(response);
			} else {
				return ResponseEntity.status(404).body("No se encontró ningún método de pago para pedidos urgentes.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error interno al consultar el método de pago.");
		}
	}
}
