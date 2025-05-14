package TestLocale;

import MatchEngineCLI.*;

import java.util.ArrayList;
import java.util.List;

public class GenerateurPrefixeTest {

    public static void main(String[] args) {
        // Création de la liste 1
        List<Nom> liste1 = new ArrayList<>();
        liste1.add(new Nom("1", "Alice", List.of("Alice")));
        liste1.add(new Nom("2", "Albert", List.of("Albert")));
        liste1.add(new Nom("3", "Bruno", List.of("Bruno")));

        // Création de la liste 2
        List<Nom> liste2 = new ArrayList<>();
        liste2.add(new Nom("4", "Alfred", List.of("Alfred")));  // Match avec Alice, Albert
        liste2.add(new Nom("5", "Brigitte", List.of("Brigitte"))); // Match avec Bruno
        liste2.add(new Nom("6", "Charlie", List.of("Charlie")));  // Pas de match

        // Instanciation du générateur
        GenerateurCandidats generateur = new GenerateurPrefixeCommun();

        // Génération des couples
        List<CoupleDeNoms> couples = generateur.generer(liste1, liste2);

        // Affichage des résultats
        System.out.println("Couples générés :");
        for (CoupleDeNoms couple : couples) {
            System.out.println(couple.nom1().getNomNonTraite() + " - " + couple.nom2().getNomNonTraite());
        }

        // Vérification simple du nombre attendu (facultatif)
        if (couples.size() == 3) {
            System.out.println("✅ Test réussi : 3 couples générés comme attendu.");
        } else {
            System.out.println("❌ Test échoué : nombre de couples inattendu.");
        }
    }
}