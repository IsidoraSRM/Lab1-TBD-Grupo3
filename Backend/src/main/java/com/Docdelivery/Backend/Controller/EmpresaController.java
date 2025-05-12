package com.Docdelivery.Backend.Controller;

import com.Docdelivery.Backend.dto.VistaEmpresaDto;
import com.Docdelivery.Backend.Service.EmpresaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/top-ranking")
    public List<VistaEmpresaDto> obtenerEmpresasConRanking1() {
        return empresaService.obtenerEmpresasConRanking1();
    }
}
