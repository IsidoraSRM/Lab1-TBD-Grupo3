package com.Alvolante.Backend.Controller;


import com.Alvolante.Backend.Entity.usuarioEntity;
import com.Alvolante.Backend.Service.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class usuarioController {
    @Autowired
    private usuarioService userservice;

    @GetMapping("/id")
    public usuarioEntity getById(@RequestParam("id") String id){
        System.out.printf("Id por buscar: " + id);
        return userservice.getUserById(Integer.parseInt(id));
    }

    //Registrar usuario
    @PostMapping("/register")
    public usuarioEntity register(@RequestBody usuarioEntity nuevo){
        return userservice.register(nuevo.getName(), nuevo.getEmail(), nuevo.getPassword(), nuevo.getRol());
    }
    //Realizar login del usuario
    @PostMapping("/login")
    public int login(@RequestBody usuarioEntity x) {
        return userservice.login(x.getEmail(), x.getPassword());
    }


}
