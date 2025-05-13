package MatchEngineCLI;
import java.util.*;
import java.util.ArrayList;

public class SelectionneurParSeuil implements Selectionneur {
    private double seuil;
    
    public SelectionneurParSeuil(double seuil) {
        this.seuil = seuil;
    }
    
    @Override
    public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
         List<CoupleAvecScore> resultat = new ArrayList<>();
        for (CoupleAvecScore couple : resultatsAvecScore) {
            if (couple.score() >= seuil) {
                resultat.add(new CoupleDeNoms(couple.nom1(), couple.nom2()));
            }
        }
        return resultat;
    }
}
