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

    public List<CoupleDeNoms> dedupliquerListe(List<Nom> liste) {
    List<CoupleAvecScore> correspondances = comparerListes(liste, liste);
    List<CoupleDeNoms> doublons = new ArrayList<>();

    for (CoupleAvecScore couple : correspondances) {
        Nom nom1 = couple.nom1();
        Nom nom2 = couple.nom2();
        double score = couple.score();

        if (!nom1.id().equals(nom2.id()) && nom1.id().compareTo(nom2.id()) < 0 && score >= 0.9) {
            doublons.add(new CoupleDeNoms(nom1, nom2));
        }
    }

    return doublons;
}


}
