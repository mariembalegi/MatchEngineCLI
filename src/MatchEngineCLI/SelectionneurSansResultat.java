package MatchEngineCLI;
import java.util.List;

public class SelectionneurSansResultat implements Selectionneur {

    @Override
    public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
        return resultatsAvecScore;
    }
}


