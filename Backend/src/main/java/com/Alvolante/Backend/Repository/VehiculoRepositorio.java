package com.Alvolante.Backend.Repository;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepositorio extends JpaRepository<VehiculoEntity, Long> {
}
