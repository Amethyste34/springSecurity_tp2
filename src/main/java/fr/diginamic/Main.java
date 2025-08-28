package fr.diginamic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Exemple de prénoms
        String[] prenoms = {"Sarah", "Dmitri", "Julien", "Angeline", "Cyril", "Sandrine", "Tommy", "Daris", "Mathieu", "Laurence","Robin", "Nuno"};

        for (String prenom : prenoms) {
            String hash = getHash(prenom);
            System.out.println(prenom + " -> " + hash);
        }
    }

    public static String getHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        // Conversion en hexadécimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}