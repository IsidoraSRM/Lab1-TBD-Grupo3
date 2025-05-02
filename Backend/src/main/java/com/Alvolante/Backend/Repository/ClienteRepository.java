package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

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
}
