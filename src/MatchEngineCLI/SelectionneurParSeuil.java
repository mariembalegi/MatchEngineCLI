import java.util.*;
import java.util.ArrayList;
public class SelectionneurParSeuil implements Selectionneur {
    private double seuil;
    public SelectionneurParSeuil(double seuil) {
        this.seuil = seuil;
        }
        @Override
        public List<List<Object>> selectionner(List<List<Object>> resultatsAvecScore) {
            List<List<Object>> resultat = new ArrayList<>();
            for (int i = 0; i < resultatsAvecScore.size(); i++) {
                List<Object> ligne = resultatsAvecScore.get(i);
                double score = (double) ligne.get(ligne.size() - 1);
                if (score >= seuil) {
                    resultat.add(ligne);
                }
            }

            return resultat;
        }
    }


