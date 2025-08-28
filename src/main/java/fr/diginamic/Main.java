package fr.diginamic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Main {

    public static void main(String[] args) {

        // SecretKey du TP
        String secretKey = "essayeDoncCetteChouetteCleSecreteOnVerraSiCaMarche";

        // JWTs à tester
        String[] jwts = {
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTMxNX0.VIBNB1C1j93PUDrbmFJwbJXXbTYNPwEGJbkEQHVZoYg",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTYwNn0.UZ6IO0Wvrnd4NP63diYjyvkNFNWI1NfDGP9lpfJyJSE",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHRpZ25lciIsImlhdCI6MTc0NDgzMTY0NX0.5vpcu1T7DmXDuoCBLhBJAQGE3HpNUO41-Tr0rkGrDY0",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGVtcGxlIiwibWVzc2FnZSI6IlZvaWNpIHVuZSBjaGHDrm5lIMOgIHNpZ25lciIsImlhdCI6MTc0NDgzMTY3OH0.NXvOGwyMKQ9tK2z5dR6ER5tbf2plLlkxgJnCQ0lI13g",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZSBuZSBzYWlzIHBhcyIsIm1lc3NhZ2UiOiLDoCBtb2kgbcOqbWUiLCJtZXNzYWdlLWNhY2jDqSI6InRyYXZhaWxsZSwgw6dhIGZpbml0IHRvdWpvdXJzIHBhciBwYXllciIsImxodW1vdXIiOiJjJ2VzdCBpbXBvcnRhbnQiLCJpYXQiOjE3NDQ4MzE5MTV9.wdaFguIzdkNKgVaYmSg5jHgYCDenufwjlJEL7T42fLA"
        };

        // Test de chaque JWT
        for (String jwt : jwts) {
            System.out.println("==== Test du JWT ====");
            System.out.println(jwt);
            try {
                Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                System.out.println("JWT valide !");
                System.out.println("Subject : " + claims.getSubject());
                System.out.println("Tous les messages : " + claims);
            } catch (Exception e) {
                System.out.println("JWT invalide ou mauvaise clé : " + e.getMessage());
            }
            System.out.println();
        }

        // Vérification d'une mauvaise clé
        String mauvaiseCle = "maToutAutantChouetteCleSecreteQueJeChoisiCommeJeVeux";
        System.out.println("=== Test d'une mauvaise clé ===");
        try {
            Key key = Keys.hmacShaKeyFor(mauvaiseCle.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwts[0])
                    .getBody();
            System.out.println("JWT valide avec mauvaise clé ? NON, ça devrait planter !");
        } catch (Exception e) {
            System.out.println("Échec attendu avec mauvaise clé : " + e.getMessage());
        }
    }
}