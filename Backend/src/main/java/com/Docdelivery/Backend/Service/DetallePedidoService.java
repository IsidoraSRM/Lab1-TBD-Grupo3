package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.Repository.DetallePedidoRepository;
import com.Docdelivery.Backend.dto.ServiciosDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<ServiciosDto> obtenerServiciosMasPedidos() {
        return detallePedidoRepository.getServiciosMasPedidosUltimoMes();
    }
}

