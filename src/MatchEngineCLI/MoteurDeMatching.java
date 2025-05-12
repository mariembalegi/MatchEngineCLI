package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private List<Pretraiteur> pretraiteurs;
    private Comparateur comparateur;
    private Selectionneur selectionneur;
    private GenerateurCandidats generateur;

    public MoteurDeMatching(List<Pretraiteur> pretraiteurs, Comparateur comparateur,
                            Selectionneur selectionneur, GenerateurCandidats generateur) {
        this.pretraiteurs = pretraiteurs;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
        this.generateur = generateur;
    }


    public List<Nom> rechercher(Nom nomRecherche, List<Nom> listeNoms) {
        List<CoupleDeNoms> candidats = generateur.generer(List.of(nomRecherche), listeNoms);
        List<CoupleAvecScore> resultatsAvecScore = new ArrayList<>();

        for (CoupleDeNoms couple : candidats) {
            double score = comparateur.comparer(couple.nom1(), couple.nom2());
            resultatsAvecScore.add(new CoupleAvecScore(couple.nom1(), couple.nom2(), score));
        }

        List<CoupleAvecScore> selectionnes = selectionneur.selectionner(resultatsAvecScore);
        List<Nom> res = new ArrayList<>();
        for (CoupleAvecScore c : selectionnes) {
            res.add(c.nom2());
        }
        return res;
    }
    
    public List<CoupleAvecScore> comparerListes(List<Nom> liste1, List<Nom> liste2) {
    List<CoupleAvecScore> correspondances = new ArrayList<>();

    for (Nom nom : liste1) {
        List<Nom> similaires = rechercher(nom, liste2);
        for (Nom sim : similaires) {
            double score = comparateur.comparer(nom, sim);
            correspondances.add(new CoupleAvecScore(nom, sim, score));
        }
    }

    return correspondances;
}

    public List<String> dedupliquerListeFormatee(List<Nom> liste) {
        List<String> doublonsFormates = new ArrayList<>();

        for (int i = 0; i < liste.size(); i++) {
            Nom nom1 = liste.get(i);
            for (int j = i + 1; j < liste.size(); j++) {
                Nom nom2 = liste.get(j);
                double score = comparateur.comparer(nom1, nom2);
                if (score >= 0.9) {
                    String ligne = String.format(
                            "Nom doublon détecté : [%s] (id=%s) ≈ [%s] (id=%s) - Score: %.2f",
                            nom1.getNomNonTraite(), nom1.getId(),
                            nom2.getNomNonTraite(), nom2.getId(),
                            score
                    );
                    doublonsFormates.add(ligne);
                }
            }
        }

        return doublonsFormates;
    }
}
