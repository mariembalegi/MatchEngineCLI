package Tests;

import MatchEngineCLI.*;
import java.util.List;
import java.util.ArrayList;

public class SelectionneurTest {
    public static void main(String[] args) {
        // Chemin SANS guillemets
        String csvPath = "C:\\Users\\PC-amal\\Desktop\\amal\\peps_names_1k.csv";

        // 1. Initialisation
        Comparateur comparateur = new ComparateurNomsTraiteJaroWinkler();
        RecuperateurCSV recuperateur = new RecuperateurCSV(csvPath);

        // Limiter le nombre de noms pour les tests
        List<Nom> noms = recuperateur.recuperer().subList(0, 10);

        // 2. Génération des couples de test
        List<CoupleAvecScore> couples = genererCouplesDeTest(noms, comparateur);

        // 3. Tests des différents sélectionneurs
        testerSelectionneur(new SelectionneurNMeilleursMax(3), "Top 3 (Max)", couples);
        testerSelectionneur(new SelectionneurNMeilleursSort(3), "Top 3 (Tri)", couples);
        testerSelectionneur(new SelectionneurParSeuil(0.75), "Seuil ≥0.75", couples);
    }

    private static List<CoupleAvecScore> genererCouplesDeTest(List<Nom> noms, Comparateur comparateur) {
        List<CoupleAvecScore> couples = new ArrayList<>();
        System.out.println("\nGénération des couples de test...");

        for (int i = 0; i < noms.size(); i++) {
            for (int j = i + 1; j < noms.size(); j++) {
                double score = comparateur.comparer(noms.get(i), noms.get(j));
                couples.add(new CoupleAvecScore(noms.get(i), noms.get(j), score));

                System.out.printf("%2d. %-20s vs %-20s → %.4f\n",
                        couples.size(),
                        noms.get(i).getNomNonTraite(),
                        noms.get(j).getNomNonTraite(),
                        score);
            }
        }
        return couples;
    }

    private static void testerSelectionneur(Selectionneur selectionneur, String titre,
                                            List<CoupleAvecScore> couples) {
        System.out.println("\n\n=== " + titre + " ===");
        System.out.println("Couples en entrée: " + couples.size());

        long debut = System.nanoTime();
        List<CoupleAvecScore> resultats = selectionneur.selectionner(couples);
        long dureeMs = (System.nanoTime() - debut) / 1_000_000;

        System.out.println("Résultats trouvés: " + resultats.size());
        System.out.println("Temps écoulé: " + dureeMs + " ms\n");

        // Affichage des 5 premiers résultats
        int limit = Math.min(5, resultats.size());
        for (int i = 0; i < limit; i++) {
            CoupleAvecScore r = resultats.get(i);
            System.out.printf("%2d. %-20s ↔ %-20s → %.4f\n",
                    i+1,
                    r.nom1().getNomNonTraite(),
                    r.nom2().getNomNonTraite(),
                    r.score());
        }
    }
}