package com.Docdelivery.Backend.Repository;

import com.Docdelivery.Backend.Entity.DetallePedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DetallePedidoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class DetallePedidoRowMapper implements RowMapper<DetallePedidoEntity> {
        @Override
        public DetallePedidoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DetallePedidoEntity(
                rs.getLong("idDetallePedido"),
                rs.getLong("idPedido"),
                rs.getLong("idServicio"),
                rs.getInt("cantidad")
            );
        }
    }

    // Guardar detalle de pedido
    public void save(DetallePedidoEntity detalle) {
        String sql = "INSERT INTO DetallePedido (idPedido, idServicio, cantidad) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, detalle.getIdPedido(), detalle.getIdServicio(), detalle.getCantidad());
    }

    // Obtener detalles por ID de pedido
    public List<DetallePedidoEntity> findByPedidoId(Long idPedido) {
        String sql = "SELECT * FROM DetallePedido WHERE idPedido = ?";
        return jdbcTemplate.query(sql, new DetallePedidoRowMapper(), idPedido);
    }
    
}
