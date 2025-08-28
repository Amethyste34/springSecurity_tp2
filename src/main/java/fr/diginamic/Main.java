package fr.diginamic;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Hash de "toto"
        String hash1 = encoder.encode("toto");
        System.out.println("Hash de 'toto' : " + hash1);

        // Hasher une seconde fois
        String hash2 = encoder.encode("toto");
        System.out.println("Hash de 'toto' une seconde fois : " + hash2);

        // Vérifier si les hashes sont identiques
        System.out.println("Hash1 equals Hash2 ? " + hash1.equals(hash2));

        // Déclaration de deux variables
        String totohaseh = encoder.encode("toto");
        String newotohaseh = encoder.encode("toto");

        System.out.println("\nRésultats matches :");
        System.out.println(encoder.matches("toto", totohaseh));     // true
        System.out.println(encoder.matches("toto", newotohaseh));  // true

        // Vérification matches avec mauvais mot de passe
        System.out.println(encoder.matches("tata", totohaseh));    // false
    }
}