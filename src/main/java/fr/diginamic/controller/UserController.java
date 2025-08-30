package fr.diginamic.controllers;

import fr.diginamic.entity.UserApp;
import fr.diginamic.repository.UserAppRepository;
import fr.diginamic.service.JwtAuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserAppRepository userRepo;

    @Autowired
    private JwtAuthentificationService jwtService;

    // 1️⃣ Créer un utilisateur
    @PostMapping("/create")
    public UserApp createUser(@RequestBody UserApp user) {
        return userRepo.save(user);
    }

    // 2️⃣ Obtenir un token si identifiants corrects
    @PostMapping("/login")
    public String login(@RequestBody UserApp user) {
        Optional<UserApp> opt = userRepo.findByUsername(user.getUsername());
        if (opt.isPresent() && opt.get().getPassword().equals(user.getPassword())) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Invalid username or password";
    }

    // 3️⃣ Vérifier un JWT
    @GetMapping("/verify-jwt/{token}")
    public boolean verifyJwt(@PathVariable String token) {
        return jwtService.validateToken(token);
    }
}