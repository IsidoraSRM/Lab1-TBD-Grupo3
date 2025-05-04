package com.Docdelivery.Backend.Repository;

import com.Docdelivery.Backend.Entity.ServiciosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ServiciosRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static class ServiciosRowMapper implements RowMapper<ServiciosEntity>{
        public ServiciosEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ServiciosEntity(
                    rs.getLong("idServicio"),
                    rs.getLong("idEmpresaAsociada"),
                    rs.getString("nombreServicio"),
                    rs.getString("descripcionServicio"),
                    rs.getInt("precioServicio"),
                    rs.getString("categoriaServicio")
            );
        }
    }
    //insertar servicio en la base de datos
    public void save(ServiciosEntity servicio) {
        String sql = "INSERT INTO servicio (idEmpresaAsociada,nombreServicio,descripcionServicio,precioServicio,categoriaServicio) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,servicio.getIdEmpresaAsociada(),servicio.getNombreServicio(),servicio.getDescripcionServicio(),servicio.getPrecioServicio(),servicio.getCatergoriaServicio());
    }
}
