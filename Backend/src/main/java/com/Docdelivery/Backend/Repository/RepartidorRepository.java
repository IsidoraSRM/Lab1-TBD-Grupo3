package com.Docdelivery.Backend.Repository;


import com.Docdelivery.Backend.Entity.RepartidorEntity;
import com.Docdelivery.Backend.dto.TopRepartidorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RepartidorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para mapear los resultados a RepartidorEntity
    private static class RepartidorRowMapper implements RowMapper<RepartidorEntity> {
        @Override
        public RepartidorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new RepartidorEntity(
                    rs.getLong("repartidor_id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getBoolean("disponible")
            );
        }
    }

    // Insertar repartidor en la base de datos
    public void save(RepartidorEntity repartidor) {
        String sql = "INSERT INTO repartidor (nombre, telefono, disponible) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, repartidor.getNombre(), repartidor.getTelefono(), repartidor.getDisponible());
    }

    // Buscar repartidor por ID
    public Optional<RepartidorEntity> findById(Long id) {
        String sql = "SELECT * FROM repartidor WHERE repartidor_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new RepartidorRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Obtener todos los repartidores
    public List<RepartidorEntity> findAll() {
        String sql = "SELECT * FROM repartidor";
        return jdbcTemplate.query(sql, new RepartidorRowMapper());
    }

    public List<TopRepartidorDto> findTop3ByRendimiento() {
        String sql = "SELECT r.repartidor_id, r.nombre, r.telefono, r.disponible, " +
                "COUNT(o.idPedido) as entregas_completadas, " +
                "COALESCE(AVG(rt.score), 0) as puntuacion_promedio, " +
                "(COUNT(o.idPedido) * 0.6 + COALESCE(AVG(rt.score), 0) * 0.4) as rendimiento " +
                "FROM repartidor r " +
                "LEFT JOIN orderentity o ON r.repartidor_id = o.repartidor_id AND o.estadoPedido = 'ENTREGADO' " +
                "LEFT JOIN rating rt ON o.idPedido = rt.idPedido " +
                "GROUP BY r.repartidor_id, r.nombre, r.telefono, r.disponible " +
                "ORDER BY rendimiento DESC " +
                "LIMIT 3";

        return jdbcTemplate.query(sql, topRepartidorRowMapper);
    }

    private final RowMapper<TopRepartidorDto> topRepartidorRowMapper = (rs, rowNum) -> {
        TopRepartidorDto dto = new TopRepartidorDto();
        dto.setRepartidorId(rs.getLong("repartidor_id"));
        dto.setNombre(rs.getString("nombre"));
        dto.setTelefono(rs.getString("telefono"));
        dto.setDisponible(rs.getBoolean("disponible"));
        dto.setEntregasCompletadas(rs.getLong("entregas_completadas"));
        dto.setPuntuacionPromedio(rs.getDouble("puntuacion_promedio"));
        dto.setRendimiento(rs.getDouble("rendimiento"));
        return dto;
    };

}
