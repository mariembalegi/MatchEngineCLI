package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateurTailleNomsTraiteIndexTest {

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

        // Définir la liste des prétraiteurs
        List<Pretraiteur> pretraiteurs = Arrays.asList(
                new PretraiteurNormalisation(),
                new PretraiteurDecomposeur()
        );

        for (String chemin : chemins) {
            System.out.println("\n--- Test avec fichier : " + chemin + " ---");

            // Chargement des noms depuis le fichier CSV
            RecuperateurCSV recuperateur = new RecuperateurCSV(chemin);
            List<Nom> noms = recuperateur.recuperer();

            // Application des prétraitements sur chaque nom
            for (Nom nom : noms) {
                List<String> nomsATraiter = nom.getNomTraite();
                for (Pretraiteur pretraiteur : pretraiteurs) {
                    List<String> nomsApresPretraitement = new ArrayList<>();
                    for (String nomStr : nomsATraiter) {
                        nomsApresPretraitement.addAll(pretraiteur.pretraiter(nomStr));
                    }
                    nomsATraiter = nomsApresPretraitement;
                }
                nom.setNomTraite(nomsATraiter);
            }

            // Instanciation du générateur avec indexation par taille de nom traité
            GenerateurTailleNomsTraiteIndex generateur = new GenerateurTailleNomsTraiteIndex();

            // Mesure du temps d'exécution
            long startTime = System.nanoTime();
            List<CoupleDeNoms> couples = generateur.generer(noms, noms);
            long endTime = System.nanoTime();

            long durationInMs = (endTime - startTime) / 1_000_000;

            // Affichage console
            System.out.println("Nombre de noms : " + noms.size());
            System.out.println("Nombre de couples générés : " + couples.size());
            System.out.println("Temps d'exécution : " + durationInMs + " ms");

            // Vérifications
            assertTrue(couples.size() > 0, "Le générateur doit produire au moins un couple");
            assertTrue(durationInMs >= 0, "Le temps d'exécution doit être positif");
        }
    }
}