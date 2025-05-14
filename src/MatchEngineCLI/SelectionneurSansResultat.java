package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSansResultat implements Selectionneur {

    @Override
    public List<CoupleAvecScore> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
        List<CoupleAvecScore> resultats = new ArrayList<>();
        
        for (CoupleAvecScore coupleAvecScore : resultatsAvecScore) {
            resultats.add(new CoupleAvecScore(coupleAvecScore.nom1(), coupleAvecScore.nom2(),coupleAvecScore.score()));
        }
        return resultats;
    }
}