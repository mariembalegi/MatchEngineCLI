package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;
public class SelectionneurNMeilleursMax implements Selectionneur {
    private final int n;
    public SelectionneurNMeilleursMax(int n) {
        this.n = n;
        }
        @Override
        public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
            List<CoupleAvecScore> meilleurs = new ArrayList<>();
            List<CoupleAvecScore> copie = new ArrayList<>(resultatsAvecScore);
            for (int i = 0; i < n && !copie.isEmpty(); i++) {
                double maxScore = -1;
                int indexMax = -1;
                for (int j = 0; j < copie.size(); j++) {
                    CoupleAvecScore courant = copie.get(j);
                    if (courant.score() > maxScore) {
                        maxScore = courant.score();
                        indexMax = j;
                    }
                }
                CoupleAvecScore meilleur = copie.remove(indexMax);
                meilleurs.add(meilleur);
            }
            return meilleurs;
        }
}




