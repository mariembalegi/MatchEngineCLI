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
        GenerateurCandidats generateur = new GenerateurCandidatsComplet(); // Nouvelle classe

        // Limiter le nombre de noms pour les tests
        List<Nom> noms = recuperateur.recuperer().subList(0, 10);

        // 2. Génération des couples de test AVEC GENERATEUR
        List<CoupleAvecScore> couples = genererCouplesDeTest(noms, comparateur, generateur);

        // 3. Tests des différents sélectionneurs
        testerSelectionneur(new SelectionneurNMeilleursMax(3), "Top 3 (Max)", couples);
        testerSelectionneur(new SelectionneurNMeilleursSort(3), "Top 3 (Tri)", couples);
        testerSelectionneur(new SelectionneurParSeuil(0.75), "Seuil ≥0.75", couples);
    }

    private static List<CoupleAvecScore> genererCouplesDeTest(List<Nom> noms,
                                                              Comparateur comparateur,
                                                              GenerateurCandidats generateur) {
        List<CoupleAvecScore> couples = new ArrayList<>();
        System.out.println("\nGénération des couples de test...");

        // Utilisation du générateur pour créer les paires
        List<CoupleDeNoms> paires = generateur.generer(noms, noms);

        for (CoupleDeNoms paire : paires) {
            // On évite de comparer un nom avec lui-même
            if (!paire.nom1().getId().equals(paire.nom2().getId())) {
                double score = comparateur.comparer(paire.nom1(), paire.nom2());
                couples.add(new CoupleAvecScore(paire.nom1(), paire.nom2(), score));

                System.out.printf("%-20s vs %-20s → %.4f\n",
                        paire.nom1().getNomNonTraite(),
                        paire.nom2().getNomNonTraite(),
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

// Implémentation basique du générateur
class GenerateurCandidatsComplet implements GenerateurCandidats {
    @Override
    public List<CoupleDeNoms> generer(List<Nom> liste1, List<Nom> liste2) {
        List<CoupleDeNoms> couples = new ArrayList<>();
        for (Nom nom1 : liste1) {
            for (Nom nom2 : liste2) {
                couples.add(new CoupleDeNoms(nom1, nom2));
            }
        }
        return couples;
    }
}
