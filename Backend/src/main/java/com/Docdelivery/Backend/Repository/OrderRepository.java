package com.Docdelivery.Backend.Repository;

import com.Docdelivery.Backend.Entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // RowMapper para OrderEntity
    private static class OrderRowMapper implements RowMapper<OrderEntity> {
        @Override
        public OrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderEntity order = new OrderEntity();
            order.setIdPedido(rs.getLong("idpedido"));
            order.setClienteId(rs.getLong("cliente_id"));
            Long repartidorId = rs.getObject("repartidor_id") != null ? rs.getLong("repartidor_id") : null;
            order.setRepartidorId(repartidorId);
            order.setFechaPedido(rs.getTimestamp("fechapedido").toLocalDateTime());
            Timestamp fechaEntrega = rs.getTimestamp("fechaEntrega");
            order.setFechaEntrega(fechaEntrega != null ? fechaEntrega.toLocalDateTime() : null);

            order.setEstadoPedido(rs.getString("estadopedido"));
            order.setPrioridadPedido(rs.getString("prioridadpedido"));
            return order;
        }
    }

    // Guardar pedido y devolver el ID generado
    public Long save(OrderEntity order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO orderentity (cliente_id, repartidor_id, fechaPedido, estadoPedido, prioridadPedido) " +
                "VALUES (?, ?, ?, ?, ?)",
                new String[] {"idpedido"}
            );
            ps.setLong(1, order.getClienteId());
            if (order.getRepartidorId() != null) {
                ps.setLong(2, order.getRepartidorId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }
            ps.setTimestamp(3, Timestamp.valueOf(order.getFechaPedido()));
            ps.setString(4, order.getEstadoPedido());
            ps.setString(5, order.getPrioridadPedido());
            return ps;
        }, keyHolder);
        
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    // Buscar pedido por ID
    public Optional<OrderEntity> findById(Long id) {
        String sql = "SELECT * FROM OrderEntity WHERE idPedido = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new OrderRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Buscar pedidos por repartidor
    public List<OrderEntity> findByRepartidorId(Long repartidorId) {
        String sql = "SELECT * FROM OrderEntity WHERE repartidor_id = ?";
        return jdbcTemplate.query(sql, new OrderRowMapper(), repartidorId);
    }

    // Obtener todos los pedidos
    public List<OrderEntity> findAll() {
        String sql = "SELECT * FROM OrderEntity";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    //Procedure registerOrder
    public int registerOrder(int clienteId, String prioridad, String nombreMetodo, int monto,String nombre_servicio,String descripcion, String categoria, String direccionInicio,String direccionDestino) {
        String query = "CALL register_order(?,?,?,?,?,?,?,?,?,?)";
        
        try {
            return jdbcTemplate.execute(
                query,
                (CallableStatementCallback<Integer>) cs -> {
                    // Configurar parámetros de entrada
                    cs.setInt(1, clienteId);
                    cs.setString(2, prioridad);
                    cs.setString(3,nombreMetodo);
                    cs.setInt(4, monto);
                    cs.setString(5, nombre_servicio);
                    cs.setString(6, descripcion);
                    cs.setString(7, categoria);
                    cs.setString(8, direccionInicio);
                    cs.setString(9, direccionDestino);
                    

                    
                    // Registrar parámetro de salida
                    cs.registerOutParameter(10, Types.INTEGER);
                    
                    // Ejecutar el procedimiento
                    cs.execute();
                    
                    // Recuperar el ID generado
                    return cs.getInt(10);
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar orden: " + e.getMessage());
            return -1;
        }
    }
    //Procedimiento para actualizar el estado de los pedidos
    public void cambiarEstadoPedido(Long idPedido, String nuevoEstado) {
        jdbcTemplate.execute(
                "CALL cambiar_estado_pedido(?, ?)",
                (PreparedStatementCallback<Boolean>) ps -> {
                    ps.setLong(1, idPedido);
                    ps.setString(2, nuevoEstado);
                    return ps.execute();
                }
        );
    }


    
    //Procedure confirmarPedido
    public int confirmarPedido(int idPedido) {
        String query = "CALL confirmar_pedido(?)";
        
        try {
            jdbcTemplate.execute(
                query,
                (CallableStatementCallback<Integer>) cs -> {
                    // parametro de entrada
                    cs.setInt(1, idPedido);
                    
                    // ejecutar el procedimiento
                    cs.execute();
                    
                    
                    return 1;
                }
            );
            return 1; // bien
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al confirmar pedido: " + e.getMessage());
            return -1; // Error
        }
    }

    //Obtener pedido con cliente y detallePedido 
    //Obtener pedido con cliente y detallePedido por idRepartidor
    public List<Map<String, Object>> getPedidosConClienteYDetalleByRepartidorId(Long repartidorId) {
        String sql = "SELECT o.*, c.*, dp.* " +
                    "FROM OrderEntity o " +
                    "JOIN cliente c ON o.cliente_id = c.cliente_id " +
                    "JOIN detallepedido dp ON o.idpedido = dp.idpedido " +
                    "WHERE o.repartidor_id = ? " +
                    "ORDER BY o.fechapedido DESC";
        
        try {
            return jdbcTemplate.queryForList(sql, repartidorId);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}






