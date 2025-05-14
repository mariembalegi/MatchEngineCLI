package MatchEngineCLI;

import java.util.ArrayList;
import java.util.List;

public class SelectionneurNMeilleursSort implements Selectionneur {
    private final int n;

    public SelectionneurNMeilleursSort(int n) {
        this.n = n;
    }
    @Override
    public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
        List<CoupleAvecScore> copie = new ArrayList<>(resultatsAvecScore);
        copie.sort((c1, c2) -> Double.compare(c2.score(), c1.score()));
        return copie.subList(0, Math.min(n, copie.size()));
    }
}

