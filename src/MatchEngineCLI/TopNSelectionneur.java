package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;
public class TopNSelectionneur implements Selectionneur {
    private int n;
    public TopNSelectionneur(int n) {
        this.n = n;
        }
        @Override
        public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
        List<CoupleAvecScore> resultat = new ArrayList<>();
        for (int i = 0; i < n && i < resultatsAvecScore.size(); i++) {
            CoupleAvecScore ligne = resultatsAvecScore.get(i);
            resultat.add(ligne);
            }
        return resultat;
        }
    }



