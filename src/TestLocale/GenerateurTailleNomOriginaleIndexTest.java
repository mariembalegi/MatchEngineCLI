package TestLocale;


import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenerateurTailleNomOriginaleIndexTest {

    @Test
    void testGenerer() {
        GenerateurTailleNomsOriginaleIndex generateur = new GenerateurTailleNomsOriginaleIndex();

        Nom nom1 = new Nom("1", "Anna", null);     // taille = 4
        Nom nom2 = new Nom("2", "Luc", null);      // taille = 3
        Nom nom3 = new Nom("3", "Émilie", null);   // taille = 6
        Nom nom4 = new Nom("4", "Bob", null);      // taille = 3
        Nom nom5 = new Nom("5", "Pauline", null);  // taille = 7
        Nom nom6 = new Nom("6", "Zoe", null);      // taille = 3

        List<Nom> liste1 = Arrays.asList(nom1, nom2); // Anna, Luc
        List<Nom> liste2 = Arrays.asList(nom3, nom4, nom5, nom6); // Émilie, Bob, Pauline, Zoe

        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);

        // Affichage (optionnel pour visualisation)
        System.out.println("Couples générés :");
        for (CoupleDeNoms couple : couples) {
            System.out.println(couple.nom1().getNomNonTraite() + " - " + couple.nom2().getNomNonTraite());
        }

        // ✅ Anna (taille 4) doit matcher avec : Émilie (6), Bob (3), Zoe (3)
        // ✅ Luc (taille 3) doit matcher avec : Émilie (6), Bob (3), Zoe (3)

        assertEquals(5, couples.size(), "Nombre attendu de couples incorrect.");

        // Tu peux aussi vérifier les couples exacts si nécessaire
        assertTrue(couples.stream().anyMatch(c -> c.nom1().getNomNonTraite().equals("Anna")
                && c.nom2().getNomNonTraite().equals("Émilie")));
    }
}