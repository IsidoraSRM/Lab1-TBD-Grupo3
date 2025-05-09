package com.Docdelivery.Backend.Repository;


import com.Docdelivery.Backend.Entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class  ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para mapear los resultados a ClienteEntity
    private static class ClienteRowMapper implements RowMapper<ClienteEntity> {
        @Override
        public ClienteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ClienteEntity(
                    rs.getLong("cliente_id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("telefono")
            );
        }
    }

    // Insertar cliente en la base de datos
    public void save(ClienteEntity cliente) {
        String sql = "INSERT INTO cliente (nombre, direccion, email, telefono) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getNombre(), cliente.getDireccion(), cliente.getEmail(), cliente.getTelefono());
    }

    // Buscar cliente por ID
    public Optional<ClienteEntity> findById(Long id) {
        String sql = "SELECT * FROM cliente WHERE cliente_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ClienteRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty(); // Manejar caso donde el cliente no existe
        }
    }

    // Obtener todos los clientes
    public List<ClienteEntity> findAll() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, new ClienteRowMapper());
    }

    //consulta numero 1
    public Optional<ClienteEntity> obtenerClienteQueMasHaGastado() {
        String sql = """ 
        SELECT 
            c.cliente_id,
            c.nombre,
            c.email,
            SUM(p.monto) AS total_gastado
        FROM OrderEntity o
        JOIN Pago p ON o.idPedido = p.idPedido
        JOIN Cliente c ON o.cliente_id = c.cliente_id
        WHERE o.estadoPedido = 'Entregado'
        GROUP BY c.cliente_id, c.nombre, c.email
        ORDER BY total_gastado DESC
        LIMIT 1
        """;

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new ClienteEntity(
                    rs.getLong("cliente_id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("telefono")
            )));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
