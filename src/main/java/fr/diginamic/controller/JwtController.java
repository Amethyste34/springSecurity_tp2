package fr.diginamic.controller;

import fr.diginamic.entity.UserApp;
import fr.diginamic.service.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private JwtAuthentificationService jwtService;

    @PostMapping("/create-jwt")
    public String createJwt(@RequestBody UserApp user) {
        return jwtService.generateToken(user.getUsername());
    }

    @GetMapping("/get-jwt")
    public String getJwt() {
        // Génère un JWT pour un utilisateur "guest"
        return jwtService.generateToken("guest");
    }
}