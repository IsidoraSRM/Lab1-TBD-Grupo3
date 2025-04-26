package com.Alvolante.Backend.Service;

import com.Alvolante.Backend.Entity.VehiculoEntity;
import com.Alvolante.Backend.Repository.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class vehiculoService {
    @Autowired
    private VehiculoRepositorio vehiRepo;

}
