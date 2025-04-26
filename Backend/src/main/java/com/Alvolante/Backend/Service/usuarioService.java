package com.Alvolante.Backend.Service;


import com.Alvolante.Backend.Entity.usuarioEntity;
import com.Alvolante.Backend.Repository.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usuarioService {
    @Autowired
    private usuarioRepository userRepo;

    public usuarioEntity getUserById(long id) {
        return userRepo.findById(id).get();
    }

    public usuarioEntity register(String name, String email, String password, String rol) {
        usuarioEntity user = new usuarioEntity(name, email, password, rol);
        usuarioEntity existente = userRepo.findByEmail(user.getEmail());
        if(existente != null){
            return null;
        }
        return userRepo.save(user);
    }

    public int login(String email, String password) {
        usuarioEntity user = userRepo.findByEmail(email);
        if(user != null) {
            if (password.equals(user.getPassword())) {
                if(user.getRol().equals("usuario")){
                    return 3;
                }
            }
        }
        return 0;
    }
}
