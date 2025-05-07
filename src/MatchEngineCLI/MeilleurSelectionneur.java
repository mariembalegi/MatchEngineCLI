package MatchEngineCLI;
import java.util.*;
import java.util.ArrayList;
public class MeilleurSelectionneur implements Selectionneur {
    @Override
    public List<List<Object>> selectionner(List<List<Object>> resultatsAvecScore) {
        double meilleurScore = -1;
        List<Object> meilleur = null;
        for (int i = 0; i < resultatsAvecScore.size(); i++) {
            List<Object> ligne = resultatsAvecScore.get(i);
            double score = (double) ligne.get(3);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleur = ligne;
                }
            }
            List<List<Object>> resultat = new ArrayList<>();
            if (meilleur != null) {
                resultat.add(meilleur);
            }
            return resultat;
        }
    }


