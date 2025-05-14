package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurTailleNomsOriginaleTest {

    @Test
    public void testGenererCouplesAvecLongueurProcheDepuisPlusieursFichiers() {
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

            RecuperateurCSV recuperateur = new RecuperateurCSV(chemin);
            List<Nom> noms = recuperateur.recuperer();

            GenerateurTailleNomsOriginale generateur = new GenerateurTailleNomsOriginale();

            long startTime = System.nanoTime();
            List<CoupleDeNoms> couples = generateur.generer(noms, noms);
            long endTime = System.nanoTime();

            long durationInMs = (endTime - startTime) / 1_000_000;

            System.out.println("Nombre de noms : " + noms.size());
            System.out.println("Nombre de couples générés : " + couples.size());
            System.out.println("Temps d'exécution : " + durationInMs + " ms");

            // Vérification : chaque couple respecte la contrainte de longueur
            for (CoupleDeNoms couple : couples) {
                int len1 = couple.nom1().getNomNonTraite().length();
                int len2 = couple.nom2().getNomNonTraite().length();
                int diff = Math.abs(len1 - len2);
                assertTrue(diff <= 2, "Différence de longueur > 2 pour le couple : " + couple);
            }

            assertTrue(durationInMs >= 0, "Le temps d'exécution doit être positif");
        }
    }
}
