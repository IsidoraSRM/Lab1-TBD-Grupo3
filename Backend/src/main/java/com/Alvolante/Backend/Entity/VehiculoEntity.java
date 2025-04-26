package com.Alvolante.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long idVehiculo;
    private String estadoVehiculo;
    private String marca;
    private String modelo;
    private String patente;
    private String numeroChasis;
    private String kilometraje;
    private float costo;
    private int anio;
    private String tipo;
    private String color;
    private int capacidadPasajeros;
    private boolean disponibilidad;
    private String fechaUltimoMantenimiento;
}
