package com.Docdelivery.Backend.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Docdelivery.Backend.Entity.OrderSummaryEntity;

@Repository
public class OrderSummaryRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderSummaryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper como clase interna
    private static class OrderSummaryRowMapper implements RowMapper<OrderSummaryEntity> {
        @Override
        public OrderSummaryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderSummaryEntity summary = new OrderSummaryEntity();
            summary.setClienteId(rs.getLong("cliente_id"));
            summary.setNombreCliente(rs.getString("nombre_cliente"));
            summary.setEmailCliente(rs.getString("email_cliente"));
            summary.setTotalPedidos(rs.getInt("total_pedidos"));
            summary.setMontoTotalGastado(rs.getDouble("monto_total_gastado"));
            return summary;
        }
    }

    public List<OrderSummaryEntity> findAll() {
        String sql = "SELECT * FROM order_summary";
        return jdbcTemplate.query(sql, new OrderSummaryRowMapper());
    }
}
