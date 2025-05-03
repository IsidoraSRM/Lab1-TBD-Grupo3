package com.Docdelivery.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiciosEntity {
    private Long idServicio;
    // S manyToOne E
    private Long idEmpresaAsociada;
    private String nombreServicio;
    private String descripcionServicio;
    private Integer precioServicio;
    private String CatergoriaServicio;
}
