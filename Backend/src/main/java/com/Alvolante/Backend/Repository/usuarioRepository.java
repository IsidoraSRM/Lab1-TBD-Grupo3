package com.Alvolante.Backend.Repository;


import com.Alvolante.Backend.Entity.usuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioRepository extends JpaRepository<usuarioEntity, Long> {
    usuarioEntity findByEmail(String email);
}
