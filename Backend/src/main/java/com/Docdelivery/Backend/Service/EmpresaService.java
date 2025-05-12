package com.Docdelivery.Backend.Service;

import com.Docdelivery.Backend.Entity.EmpresaAsociadaEntity;
import com.Docdelivery.Backend.Repository.EmpresaRepository;
import com.Docdelivery.Backend.dto.VistaEmpresaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // 🔹 Obtener empresas con ranking 1
    public List<VistaEmpresaDto> obtenerEmpresasConRanking1() {
        return empresaRepository.obtenerEmpresasConRanking1();
    }

    // 📄 Obtener todas las empresas con paginación
    public List<EmpresaAsociadaEntity> findAll(int limit, int offset) {
        return empresaRepository.findAll(limit, offset);
    }

    // 🔍 Buscar empresa por ID
    public Optional<EmpresaAsociadaEntity> findById(Long id) {
        return empresaRepository.findById(id);
    }

    // 📝 Crear o actualizar empresa
    public void save(EmpresaAsociadaEntity empresa) {
        empresaRepository.save(empresa);
    }

    // ❌ Eliminar empresa por ID
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
