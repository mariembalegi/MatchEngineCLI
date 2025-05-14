package Tests;

import MatchEngineCLI.*;

import java.util.List;

public class TestComp {
    public static void main(String[] args) {
        // Création de l'instance du comparateur
        ComparateurNomsOriginalJaroWinkler comparateur1 = new ComparateurNomsOriginalJaroWinkler();
        ComparateurNomsTraiteJaroWinkler comparateur2 = new ComparateurNomsTraiteJaroWinkler();
        ComparateurNomsOriginalLevenshtein comparateur3 = new ComparateurNomsOriginalLevenshtein();
        ComparateurNomsTraiteLevenshtein comparateur = new ComparateurNomsTraiteLevenshtein();
        ComparateurNomsOriginalExacte comparateur5 = new ComparateurNomsOriginalExacte();
        ComparateurNomsTraiteExacte comparateur6 = new ComparateurNomsTraiteExacte();
        // Création de l'instance du prétraiteur
    /*    PretraiteurNormalisation pretraiteur1 = new PretraiteurNormalisation();
        PretraiteurDecomposeur pretraiteur = new PretraiteurDecomposeur();
        PretraiteurMetaphone pretraiteur3 = new PretraiteurMetaphone();
        PretraiteurSoundex pretraiteur4 = new PretraiteurSoundex();

     */
        RecuperateurCSV recuperateur = new RecuperateurCSV("C:\\Users\\khals_\\OneDrive\\Bureau\\names_matching_peps\\peps_names_658k.csv");
        List<Nom> noms = recuperateur.recuperer();

/*        // Exécution des tests du prétraiteur
        System.out.println("Tests du Pretraiteur :");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < noms.size(); i++) {
            String input = noms.get(i).toString();
            List<String> result = pretraiteur.pretraiter(input);
            System.out.printf("Test %2d: Input: '%s' -> Output: %s%n",
                    i + 1, input, result);
        }
*/
        // Exécution des tests du comparateur
        System.out.println("Tests du Comparateur :");
        System.out.println("---------------------------------------------");
        long startTime = System.nanoTime();
        for (int i = 0; i < noms.size(); i++) {
            Nom nom1 = noms.get(i);
            Nom nom2 = noms.get(i);
            double score = comparateur.comparer(nom1, nom2);
            System.out.printf("Test %2d: '%s' vs '%s' -> Score: %.4f%n",
                    i + 1, nom1.getNomNonTraite(), nom2.getNomNonTraite(), score);
        }
        long endTime = System.nanoTime();

        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println("Nombre de noms récupérés : " + noms.size());
        System.out.println("Temps d'exécution : " + durationInMillis + " ms");
    }
}
