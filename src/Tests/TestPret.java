package Tests;

import MatchEngineCLI.*;

import java.util.List;

public class TestPret {
    public static void main(String[] args) {

        // Création de l'instance du prétraiteur
        PretraiteurNormalisation pretraiteur = new PretraiteurNormalisation();
        PretraiteurDecomposeur pretraiteur2 = new PretraiteurDecomposeur();
        PretraiteurMetaphone pretraiteur3 = new PretraiteurMetaphone();
        PretraiteurSoundex pretraiteur4 = new PretraiteurSoundex();

        RecuperateurCSV recuperateur = new RecuperateurCSV("C:\\Users\\khals_\\OneDrive\\Bureau\\names_matching_peps\\peps_names_2k.csv");
        List<Nom> noms = recuperateur.recuperer();

        // Exécution des tests du prétraiteur
        System.out.println("Tests du Pretraiteur :");
        System.out.println("---------------------------------------------");
        long startTime = System.nanoTime();
        for (int i = 0; i < noms.size(); i++) {
            String input = noms.get(i).toString();
            List<String> result = pretraiteur.pretraiter(input);
            System.out.printf("Test %2d: Input: '%s' -> Output: %s%n",
                    i + 1, input, result);
        }
        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println("Nombre de noms récupérés : " + noms.size());
        System.out.println("Temps d'exécution : " + durationInMillis + " ms");

    }
}
