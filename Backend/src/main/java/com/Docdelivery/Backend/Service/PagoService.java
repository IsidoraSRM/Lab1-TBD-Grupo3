package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.Entity.PagoEntity;
import com.Docdelivery.Backend.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

	@Autowired
	private PagoRepository pagoRepository;

	public List<PagoEntity> getAllPagos() {
		return pagoRepository.findAll();
	}

	public Optional<PagoEntity> getPagoById(Long idPago) {
		return pagoRepository.findById(idPago);
	}

	public void createPago(PagoEntity pago) {
		pagoRepository.save(pago);
	}

	public String getMedioDePagoMasUsadoEnUrgentes() {
		return pagoRepository.findMedioDePagoMasUsadoEnUrgentes();
	}
}
