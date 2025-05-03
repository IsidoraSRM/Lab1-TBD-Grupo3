package com.Docdelivery.Backend.Repository;

import com.Docdelivery.Backend.Entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
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
            order.setRepartidorId(rs.getObject("repartidor_id", Long.class)); // Puede ser null
            order.setFechaPedido(rs.getTimestamp("fechapedido").toLocalDateTime());
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


}
