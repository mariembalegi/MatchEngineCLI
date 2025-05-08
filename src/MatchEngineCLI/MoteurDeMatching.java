package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class MoteurDeMatching {

    private List<Pretraiteur> pretraiteurs;
    private Comparateur comparateur;
    private Selectionneur selectionneur;
    private GenerateurCandidats generateur;

    public MoteurDeMatching(List<Pretraiteur> pretraiteurs, Comparateur comparateur, Selectionneur selectionneur, GenerateurCandidats generateur) {
        this.pretraiteurs = pretraiteurs;
        this.comparateur = comparateur;
        this.selectionneur = selectionneur;
        this.generateur = generateur;
    }

    public List<Nom> rechercher(Nom nomRecherche,List<Nom> listeNoms) {
        List<List<Nom>> candidats = generateur.generer(List.of(nomRecherche),listeNoms);
        List<List<Object>> resultatsAvecScore = new ArrayList<>();
        for (List<Nom> coupleCandidat : candidats) {
            double score = comparateur.comparer(coupleCandidat.get(0), coupleCandidat.get(1));
            List<Object> resultat = new ArrayList<>();
            resultat.add(coupleCandidat.get(0));
            resultat.add(coupleCandidat.get(1));
            resultat.add(score);
            System.out.println(score);
            resultatsAvecScore.add(resultat);
        }
        List<List<Object>> listRes = selectionneur.selectionner(resultatsAvecScore);
        List<Nom> res = new ArrayList<>();
        for (List<Object> resultat : listRes) {
            res.add((Nom) resultat.get(1));
        }
        return res;
    }
}

