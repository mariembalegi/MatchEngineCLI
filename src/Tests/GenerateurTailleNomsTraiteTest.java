package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurTailleNomsTraiteTest {

    @Test
    public void testGenererCouplesAvecPretraitementDepuisFichiers() {
        String[] chemins = {
                "C:\\Users\\mariem\\Downloads\\peps_names_100.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_200.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_400.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_800.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_1k.csv",
                "C:\\Users\\mariem\\Downloads\\peps_names_2k.csv"
        };

        // Liste des prétraiteurs appliqués en chaîne
        List<Pretraiteur> pretraiteurs = Arrays.asList(
                new PretraiteurNormalisation(),
                new PretraiteurDecomposeur()
        );

        for (String chemin : chemins) {
            System.out.println("\n--- Test avec fichier : " + chemin + " ---");

            // Récupération des noms
            RecuperateurCSV recuperateur = new RecuperateurCSV(chemin);
            List<Nom> noms = recuperateur.recuperer();

            // Prétraitement
            for (Nom nom : noms) {
                List<String> nomTraite = nom.getNomTraite();
                for (Pretraiteur pretraiteur : pretraiteurs) {
                    List<String> resultat = new ArrayList<>();
                    for (String element : nomTraite) {
                        resultat.addAll(pretraiteur.pretraiter(element));
                    }
                    nomTraite = resultat;
                }
                nom.setNomTraite(nomTraite);
            }

            // Instanciation du générateur SANS indexation
            GenerateurTailleNomsTraite generateur = new GenerateurTailleNomsTraite();

            // Mesure du temps d’exécution
            long startTime = System.nanoTime();
            List<CoupleDeNoms> couples = generateur.generer(noms, noms);
            long endTime = System.nanoTime();

            long durationInMs = (endTime - startTime) / 1_000_000;

            // Affichage
            System.out.println("Nombre de noms : " + noms.size());
            System.out.println("Nombre de couples générés : " + couples.size());
            System.out.println("Temps d'exécution : " + durationInMs + " ms");

            // Vérifications
            assertTrue(couples.size() > 0, "Le générateur doit produire au moins un couple");
            assertTrue(durationInMs >= 0, "Le temps d'exécution doit être positif");
        }
    }
}