package Tests;

import MatchEngineCLI.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RecuperateurCSVTest {

    @Test
    public void testRecuperationDepuisUnFichier() {
        // Remplace ce chemin par le chemin réel de ton fichier
        String filePath = "C:\\Users\\mariem\\Downloads\\peps_names_200.csv";

        Path path = Paths.get(filePath);
        RecuperateurCSV recuperateur = new RecuperateurCSV(path.toString());

        long startTime = System.nanoTime();
        List<Nom> noms = recuperateur.recuperer();
        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println("Nombre de noms récupérés : " + noms.size());
        System.out.println("Temps d'exécution : " + durationInMillis + " ms");

        for (Nom nom : noms) {
            System.out.println(nom);
        }
    }
}