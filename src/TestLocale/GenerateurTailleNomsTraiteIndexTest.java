package TestLocale;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurTailleNomsTraiteIndexTest {

    @Test
    public void testGenerer() {
        // Créer des noms avec différentes tailles de nomTraite
        Nom nom1 = new Nom("1", "Alice", Arrays.asList("a", "li", "ce"));
        Nom nom2 = new Nom("2", "Bob", Arrays.asList("b", "ob"));
        Nom nom3 = new Nom("3", "Charlie", Arrays.asList("ch", "ar", "li", "e","4","jh"));
        Nom nom4 = new Nom("4", "Dan", Arrays.asList("d", "an"));
        Nom nom5 = new Nom("5", "Eve", Arrays.asList("ev"));

        List<Nom> liste1 = Arrays.asList(nom1, nom2);  // Alice (3), Bob (2)
        List<Nom> liste2 = Arrays.asList(nom3, nom4, nom5); // Charlie (6), Dan (2), Eve (1)

        GenerateurTailleNomsTraiteIndex generateur = new GenerateurTailleNomsTraiteIndex();
        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);

        // Affichage pour vérification visuelle
        System.out.println("Couples générés :");
        for (CoupleDeNoms c : couples) {
            System.out.println(c.nom1().getNomNonTraite() + " - " + c.nom2().getNomNonTraite());
        }

        assertEquals(4, couples.size(), "Nombre de couples incorrect.");
    }
}