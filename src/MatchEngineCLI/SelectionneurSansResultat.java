package MatchEngineCLI;
import java.util.ArrayList;
import java.util.List;

public class SelectionneurSansResultat implements Selectionneur {

    @Override
    public List<CoupleDeNoms> selectionner(List<CoupleAvecScore> resultatsAvecScore) {
        List<CoupleDeNoms> resultats = new ArrayList<>();
        
        for (CoupleAvecScore couple : resultatsAvecScore) {
            resultats.add(new CoupleDeNoms(couple.nom1(), couple.nom2()));
        }
        
        return resultats;
    }
}