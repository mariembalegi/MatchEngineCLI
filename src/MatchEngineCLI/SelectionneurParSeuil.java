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
            for (int i = 0; i < resultatsAvecScore.size(); i++) {
                CoupleAvecScore ligne = resultatsAvecScore.get(i);
                double score =  ligne.score();
                if (score >= seuil) {
                    resultat.add(ligne);
                }
            }

            return resultat;
        }
    }


