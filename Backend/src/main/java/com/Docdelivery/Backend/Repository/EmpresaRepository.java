package com.Docdelivery.Backend.Repository;



import com.Docdelivery.Backend.dto.VistaEmpresaDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpresaRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmpresaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VistaEmpresaDto> obtenerEmpresasConRanking1() {
        String sql = """
        WITH Ranking AS (
            SELECT empresa, total_servicios_entregados,
                   RANK() OVER (ORDER BY total_servicios_entregados DESC) AS ranking
            FROM VistaEmpresasTopServicios
        )
        SELECT empresa, total_servicios_entregados, ranking
        FROM Ranking
        WHERE ranking = 1;
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new VistaEmpresaDto(
                        rs.getString("empresa"),
                        rs.getInt("total_servicios_entregados"),
                        rs.getInt("ranking") // Agregar ranking en el DTO
                ));
    }
}
