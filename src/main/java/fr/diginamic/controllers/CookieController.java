package fr.diginamic.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie() {

        // Définition du nom et de la valeur du cookie
        String cookieName = "monCookie";
        String cookieValue = "valeurMagique";

        // Création du cookie
        ResponseCookie tokenCookie = ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(true)   // optionnel : empêche l'accès JS
                .secure(false)    // true si HTTPS
                .path("/")        // accessible sur tout le site
                .maxAge(3600)     // durée de vie en secondes
                .build();

        // Renvoi dans la ResponseEntity
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                .body("Cookie posé avec succès");
    }
}