package com.Docdelivery.Backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Docdelivery.Backend.Entity.DetallePedidoEntity;
import com.Docdelivery.Backend.Entity.OrderEntity;
import com.Docdelivery.Backend.Repository.DetallePedidoRepository;
import com.Docdelivery.Backend.Repository.OrderRepository;
import com.Docdelivery.Backend.dto.CrearPedidoDto;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Long crearPedido(CrearPedidoDto pedidoDto) {
        // Crear la entidad de pedido
        OrderEntity order = new OrderEntity();
        order.setClienteId(pedidoDto.getClienteId());
        order.setFechaPedido(LocalDateTime.now());
        order.setEstadoPedido("PENDIENTE");
        order.setPrioridadPedido(pedidoDto.getPrioridadPedido());
        
        // Guardar el pedido primero para obtener su ID
        Long idPedido = orderRepository.save(order);
        
        // Procesar cada detalle del pedido
        
        DetallePedidoEntity detalle = new DetallePedidoEntity();
        detalle.setIdPedido(idPedido);
        detalle.setIdServicio(pedidoDto.getIdServicio());
        detalle.setCantidad(pedidoDto.getCantidad());
        detallePedidoRepository.save(detalle);
        
        
        return idPedido;
    }

    public Optional<OrderEntity> getPedidoById(Long id) {
        return orderRepository.findById(id);
    }

    public List<DetallePedidoEntity> getDetallesByPedidoId(Long idPedido) {
        return detallePedidoRepository.findByPedidoId(idPedido);
    }

    //Procedure registerOrder
    public int registerOrder(int clienteId, int empresaId, String prioridad) {
        return orderRepository.registerOrder(clienteId, empresaId, prioridad);
    }

    //Procedure-> cambiarEstadoPedido
    public  void  cambiarEstadoPedido(Long idPedido, String nuevoEstado) {
        orderRepository.cambiarEstadoPedido(idPedido, nuevoEstado);
    }
    
}
