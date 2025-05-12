package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.dto.VistaEmpresaDto;
import com.Docdelivery.Backend.Repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<VistaEmpresaDto> obtenerEmpresasConRanking1() {
        return empresaRepository.obtenerEmpresasConRanking1();
    }
}
