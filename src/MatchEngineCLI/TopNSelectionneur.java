package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;
public class TopNSelectionneur implements Selectionneur {
    private int n;
    public TopNSelectionneur(int n) {
        this.n = n;
        }
        @Override
        public List<List<Object>> selectionner(List<List<Object>> resultatsAvecScore) {
        List<List<Object>> resultat = new ArrayList<>();
        for (int i = 0; i < n && i < resultatsAvecScore.size(); i++) {
            List<Object> ligne = resultatsAvecScore.get(i);
            resultat.add(ligne);
            }
        return resultat;
        }
    }



