// src/main/java/com/Docdelivery/Backend/Service/RepartidorService.java
package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.dto.TopRepartidorDto;
import com.Docdelivery.Backend.Repository.RepartidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepartidorService {

    @Autowired
    private RepartidorRepository repartidorRepository;

    public List<TopRepartidorDto> findTop3ByRendimiento() {
        return repartidorRepository.findTop3ByRendimiento();
    }

        public Optional<Long> getRepartidorIdByUsuarioId(Long usuarioId) {
    return repartidorRepository.findRepartidorIdByUsuarioId(usuarioId);
}
}