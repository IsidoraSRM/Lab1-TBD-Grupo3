package com.Alvolante.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiciosEntity {
    private Long idServicio;
    private String nombreServicio;
    private String CatergoriaServicio;
}
