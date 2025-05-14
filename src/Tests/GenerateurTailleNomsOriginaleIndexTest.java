package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurTailleNomsOriginaleIndexTest {

    @Test
    public void testGenererCouplesDepuisPlusieursFichiers() {
        String[] chemins = {
                "C:\\Users\\mariem\\Downloads\\peps_names_100.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_200.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_400.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_800.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_1k.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_2k.csv"
        };

        for (String chemin : chemins) {
            System.out.println("\n--- Test avec fichier : " + chemin + " ---");

            // Chargement des noms depuis le fichier CSV
            RecuperateurCSV recuperateur = new RecuperateurCSV(chemin);
            List<Nom> noms = recuperateur.recuperer();

            // Instanciation du générateur optimisé avec indexation par taille
            GenerateurTailleNomsOriginaleIndex generateur = new GenerateurTailleNomsOriginaleIndex();

            // Mesure du temps d’exécution
            long startTime = System.nanoTime();
            List<CoupleDeNoms> couples = generateur.generer(noms, noms);
            long endTime = System.nanoTime();

            // Durée en millisecondes
            long durationInMs = (endTime - startTime) / 1_000_000;

            // Affichage console
            System.out.println("Nombre de noms : " + noms.size());
            System.out.println("Nombre de couples générés : " + couples.size());
            System.out.println("Temps d'exécution : " + durationInMs + " ms");

            // Vérifications de base
            assertTrue(couples.size() > 0, "Le générateur doit produire au moins un couple");
            assertTrue(durationInMs >= 0, "Le temps d'exécution doit être positif");
        }
    }
}