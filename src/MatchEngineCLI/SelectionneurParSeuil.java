package MatchEngineCLI;
import java.util.*;
import java.util.ArrayList;

public class SelectionneurParSeuil implements Selectionneur {
    private final double seuil;
    
    public SelectionneurParSeuil(double seuil) {
        this.seuil = seuil;
    }
    
    @Override
    public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
         List<CoupleAvecScore> resultat = new ArrayList<>();
         for (CoupleAvecScore coupleAvecScore : resultatsAvecScore) {
            if (coupleAvecScore.score() >= seuil) { //valeur minimale
                resultat.add(new CoupleAvecScore(coupleAvecScore.nom1(), coupleAvecScore.nom2(), coupleAvecScore.score()));
            }
         }
        return resultat;
    }
}
